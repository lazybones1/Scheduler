package com.mobileapp.scheduler.ui.memo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mobileapp.scheduler.Async.MemoShowAsync;
import com.mobileapp.scheduler.Async.MemoShowWantAsync;
import com.mobileapp.scheduler.R;
import com.mobileapp.scheduler.adapter.MemoAdapter;
import com.mobileapp.scheduler.entity.Memo;
import com.mobileapp.scheduler.room.MemoRoomDatabase;
import com.mobileapp.scheduler.ui.calender.AddCalendarActivty;
import com.mobileapp.scheduler.decoartor.EventDecorator;
import com.mobileapp.scheduler.decoartor.SaturdayDecorator;
import com.mobileapp.scheduler.decoartor.SundayDecorator;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MemoFragment extends Fragment {

    public static final int MEMO_REQUEST_CODE = 101;

    MaterialCalendarView materialCalendarView;
    MemoRoomDatabase db;
    RecyclerView recyclerView;
    List<Memo> memoList = new ArrayList<>();

    private FloatingActionButton fab;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_memo, container, false);

        materialCalendarView = root.findViewById(R.id.memoView);
        recyclerView = root.findViewById(R.id.memoRecyclerView);
        initCalendarview();

        String today = Integer.toString(CalendarDay.today().getYear()) + "-" + Integer.toString(CalendarDay.today().getMonth()+1) + "-" + Integer.toString(CalendarDay.today().getDay());
        Log.e("오늘", "" +today);

        try {
            memoList = new MemoShowWantAsync(db.memoDao(),today).execute().get();
            Log.e("리스트", ""+memoList.size());
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            MemoAdapter memoAdapter = new MemoAdapter(memoList, getActivity(), getActivity(), today);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(memoAdapter);
        }

        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull @NotNull MaterialCalendarView widget, @NonNull @NotNull CalendarDay date, boolean selected) {
                //날자 선택시 해당 날자 이벤트 리사이클러뷰 추가
                String day = Integer.toString(date.getYear()) + "-" +Integer.toString(date.getMonth()+1) + "-" + Integer.toString(date.getDay());
                Log.e("클릭", "" + day);
                try {
                    memoList = new MemoShowWantAsync(db.memoDao(), day).execute().get();
                    Log.e("리스트", ""+memoList.size());
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    MemoAdapter memoAdapter = new MemoAdapter(memoList, getActivity(), getActivity(), day);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recyclerView.setAdapter(memoAdapter);
                }
            }
        });

        fab = root.findViewById(R.id.memofab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), AddMemoActivty.class);
                getActivity().startActivityForResult(intent, MEMO_REQUEST_CODE);
            }
        });

        return root;
    }

    private void initCalendarview(){
        materialCalendarView.setSelectedDate(CalendarDay.today());

        db = MemoRoomDatabase.getDatabase(getActivity());

        Collection<CalendarDay> dates = new HashSet<>();
        List<Memo> tmplist = new ArrayList<>();
        try {
            tmplist = new MemoShowAsync(db.memoDao()).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(int i = 0; i<tmplist.size(); i++){
            String[] day1 = tmplist.get(i).memoDay.split("-");
            dates.add(CalendarDay.from(Integer.parseInt(day1[0]), Integer.parseInt(day1[1])-1, Integer.parseInt(day1[2])));
            materialCalendarView.addDecorators(new EventDecorator(Color.RED, dates));
        }

        materialCalendarView.addDecorators(
                new SundayDecorator(),
                new SaturdayDecorator()
//                new EventDecorator(Color.RED, Collections.singleton(CalendarDay.today())),
        );
    }
}