package com.mobileapp.scheduler.ui.calender;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.mobileapp.scheduler.Async.CalendarShowAsync;
import com.mobileapp.scheduler.Async.CalendarShowWantAsync;
import com.mobileapp.scheduler.R;
import com.mobileapp.scheduler.adapter.CalendarAdapter;
import com.mobileapp.scheduler.entity.Calendar;
import com.mobileapp.scheduler.room.CalendarRoomDatabase;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ExecutionException;

//import com.mobileapp.scheduler.databinding.FragmentHomeBinding;

public class CalendarFragment extends Fragment {
    public static final int Calendar_REQUEST_CODE = 100;

    MaterialCalendarView materialCalendarView;
    CalendarRoomDatabase db;
    RecyclerView recyclerView;
    List<Calendar> calendarList = new ArrayList<>();

    private FloatingActionButton fab;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_calender, container, false);

        materialCalendarView = root.findViewById(R.id.calendarView);
        recyclerView = root.findViewById(R.id.calendarRecyclerView);
        initCalendarview();

        String today = Integer.toString(CalendarDay.today().getYear()) + "-" + Integer.toString(CalendarDay.today().getMonth()+1) + "-" + Integer.toString(CalendarDay.today().getDay());
        Log.e("오늘", "" +today);

        try {
            calendarList = new CalendarShowWantAsync(db.calendarDao(),today).execute().get();
            Log.e("리스트", ""+calendarList.size());
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            CalendarAdapter calendarAdapter = new CalendarAdapter(calendarList, getActivity());
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(calendarAdapter);
        }

        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull @NotNull MaterialCalendarView widget, @NonNull @NotNull CalendarDay date, boolean selected) {
                //날자 선택시 해당 날자 이벤트 리사이클러뷰 추가
                String day = Integer.toString(date.getYear()) + "-" +Integer.toString(date.getMonth()+1) + "-" + Integer.toString(date.getDay());
                Log.e("클릭", "" + day);
                try {
                    calendarList = new CalendarShowWantAsync(db.calendarDao(), day).execute().get();
                    Log.e("리스트", ""+calendarList.size());
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    CalendarAdapter calendarAdapter = new CalendarAdapter(calendarList, getActivity());
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recyclerView.setAdapter(calendarAdapter);
                }
            }
        });

        fab = root.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), AddCalendarActivty.class);
                getActivity().startActivityForResult(intent, Calendar_REQUEST_CODE);
            }
        });

        return root;
    }

    private void initCalendarview(){
        materialCalendarView.setSelectedDate(CalendarDay.today());

        db = CalendarRoomDatabase.getDatabase(getActivity());

        Collection<CalendarDay> dates = new HashSet<>();
        List<Calendar> tmplist = new ArrayList<>();
        try {
            tmplist = new CalendarShowAsync(db.calendarDao()).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(int i = 0; i<tmplist.size(); i++){
            Log.e("겟올"," "+tmplist.size()+ " " + tmplist.get(i).startDay);
            String[] day1 = tmplist.get(i).startDay.split("-");
            dates.add(CalendarDay.from(Integer.parseInt(day1[0]), Integer.parseInt(day1[1])-1, Integer.parseInt(day1[2])));
            materialCalendarView.addDecorators(new EventDecorator(Color.RED, dates));
        }

        materialCalendarView.addDecorators(
                new SundayDecorator(),
                new SaturdayDecorator()
//                new EventDecorator(Color.RED, Collections.singleton(CalendarDay.today())),
        );
    }

    public void refreshFragment(){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(this).attach(this).commit();
    }
}