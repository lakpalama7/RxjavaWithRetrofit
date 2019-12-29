package com.liveinbits.rxjavafirstproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.liveinbits.rxjavafirstproject.remote.APIService;
import com.liveinbits.rxjavafirstproject.remote.RetroInstane;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class JsonActivity extends AppCompatActivity {


    private ListView listView;
    private CustomAdapter adapter;
    private List<User> users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);
        listView=findViewById(R.id.listview);

        APIService service= RetroInstane.getService();
        service.getAllUser().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
              .subscribe(new Observer<List<User>>() {
                  @Override
                  public void onSubscribe(Disposable d) {

                  }

                  @Override
                  public void onNext(List<User> value) {
                        users=value;
                        adapter=new CustomAdapter(users);
                        listView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                  }

                  @Override
                  public void onError(Throwable e) {
                    Log.i("error ","bad");
                  }

                  @Override
                  public void onComplete() {
                    Log.i("success ","ok");
                  }
              });

    }
}
