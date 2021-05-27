package com.mobileapp.scheduler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.firebase.auth.FirebaseAuth;
import com.mobileapp.scheduler.Async.CalendarInsertAsync;
import com.mobileapp.scheduler.Async.DiaryInsertAsync;
import com.mobileapp.scheduler.Async.MemoInsertAsync;
import com.mobileapp.scheduler.databinding.ActivityMainBinding;
import com.mobileapp.scheduler.entity.Calendar;
import com.mobileapp.scheduler.entity.Diary;
import com.mobileapp.scheduler.entity.Memo;
import com.mobileapp.scheduler.room.CalendarRoomDatabase;
import com.mobileapp.scheduler.room.DiaryRoomDatabase;
import com.mobileapp.scheduler.room.MemoRoomDatabase;
import com.mobileapp.scheduler.ui.calender.CalendarFragment;
import com.mobileapp.scheduler.ui.diary.DiaryFragment;
import com.mobileapp.scheduler.ui.membership.SigninActivity;
import com.mobileapp.scheduler.ui.memo.MemoFragment;

public class MainActivity extends AppCompatActivity {
    private Button loginBtn;

    private DrawerLayout drawerLayout;
    private View drawerView;
    private ActivityMainBinding binding;
    private long backKeyPressedTime = 0;

    CalendarRoomDatabase calendarDB;
    MemoRoomDatabase memoDB;
    DiaryRoomDatabase diaryDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        calendarDB = CalendarRoomDatabase.getDatabase(this);
        memoDB = MemoRoomDatabase.getDatabase(this);
        diaryDB = DiaryRoomDatabase.getDatabase(this);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_calendar, R.id.navigation_memo, R.id.navigation_diary, R.id.navigation_timer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);


        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerView = (View) findViewById(R.id.drawer);

        //navigation drawer
        drawerLayout.setDrawerListener(listener);
        drawerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        loginBtn = findViewById(R.id.button_login);
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            loginBtn.setText("로그인");
        } else {
            loginBtn.setText("로그아웃");
        }

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    //navigation drawer
    DrawerLayout.DrawerListener listener = new DrawerLayout.DrawerListener() {
        @Override
        public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
        }

        @Override
        public void onDrawerOpened(@NonNull View drawerView) {
        }

        @Override
        public void onDrawerClosed(@NonNull View drawerView) {
        }

        @Override
        public void onDrawerStateChanged(int newState) {
        }
    };

    private void login() {
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            Intent intent = new Intent(MainActivity.this, SigninActivity.class);
            startActivity(intent);
        } else {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            Toast.makeText(this, "\'뒤로\' 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            super.onBackPressed();
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    }

    public void addMemo() {

    }

    public void addDiary() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       if (requestCode == CalendarFragment.Calendar_REQUEST_CODE) {
            if (resultCode != Activity.RESULT_OK) {
                return;
            }
           Calendar calendar = new Calendar();
           calendar.setCalendarName(data.getStringExtra("c_cname"));
           calendar.setStartDay(data.getStringExtra("c_sDate"));
           calendar.setStartTime(data.getStringExtra("c_sTime"));
           calendar.setEndTime(data.getStringExtra("c_eTime"));
           calendar.setCalendar_memo(data.getStringExtra("c_cMemo"));

           new CalendarInsertAsync(calendarDB.calendarDao()).execute(calendar);

           Intent intent = getIntent();
           finish();
           startActivity(intent);
        }
       else if (requestCode == MemoFragment.MEMO_REQUEST_CODE) {
           if (resultCode != Activity.RESULT_OK) {
               return;
           }
           Memo memo = new Memo();
           memo.setMemoName(data.getStringExtra("mName"));
           memo.setMemoContent(data.getStringExtra("mMemo"));
           memo.setMemoDay(data.getStringExtra("mDate"));

           new MemoInsertAsync(memoDB.memoDao()).execute(memo);

           Intent intent = getIntent();
           finish();
           startActivity(intent);
       }
       else if (requestCode == DiaryFragment.DIARY_REQUEST_CODE) {
            if (resultCode != Activity.RESULT_OK) {
                return;
            }
           Diary diary = new Diary();
           diary.setDiaryName(data.getStringExtra("dName"));
           diary.setDiary_weather(data.getStringExtra("mWeather"));
           diary.setDiary_day(data.getStringExtra("dDate"));
           diary.setDiary_memo(data.getStringExtra("dMemo"));

           new DiaryInsertAsync(diaryDB.diaryDao()).execute(diary);

            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }
    }
}