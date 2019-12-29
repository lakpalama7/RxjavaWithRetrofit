package com.liveinbits.rxjavafirstproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private TextView title;
    private EditText name;
    private Button btn;
    private Observable<String> observable;
    private Observer<String> observer;
    private Button btn1,btn2,btn3,btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=findViewById(R.id.btn);
        title=findViewById(R.id.title);
        name=findViewById(R.id.inputtext);
        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);
        btn3=findViewById(R.id.btn3);
        btn4=findViewById(R.id.btn4);
        createObservable();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                observable.subscribe(observer);
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Observable<String> observableObj=getObserableData();
                Observer<String> observerObj=getObserver();

                observableObj.observeOn(Schedulers.io())
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .subscribe(observerObj);*/

                Single<String> singleObservable=getSingleObservableData();
                singleObservable.observeOn(Schedulers.io())
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .subscribe(new SingleObserver<String>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onSuccess(String s) {
                                Log.i("Success ", s);

                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        });
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Single<Book> singleObserverable=getSingleBookData();
                singleObserverable.observeOn(Schedulers.io())
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .subscribe(new SingleObserver<Book>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onSuccess(Book book) {
                                Log.i("Book Name : ", book.getBookname());
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.i("Error :",e.getMessage());
                            }
                        });
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Maybe<String> maybeobj=getMaybe();
                maybeobj.observeOn(Schedulers.io())
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .subscribe(new MaybeObserver<String>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onSuccess(String s) {
                                Log.i("Name :",s);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {
                                Log.i("Complete :","Success");
                            }
                        });
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Completable.fromAction(new Action() {
                    @Override
                    public void run() throws Exception {
                            test();
                    }
                }).observeOn(Schedulers.io()).subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                            Log.i("Complete :","Completeable");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
               /* Observable<Integer> observable=Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                        for(int i=0;i<100;i++){
                            emitter.onNext(i);
                        }
                        emitter.onComplete();
                    }
                });

                observable.observeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.i("Number :",""+integer);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.i("Complete :","success");
                    }
                });*/
               /* Observable.range(1,100).observeOn(Schedulers.io()).subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.i("Integer :", ""+integer);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.i("Complete :", "success");

                    }
                });*/
            }
        });
    }

    private void test() {
        for(int i=0;i<10;i++){
            Log.i("Number :",""+i);
        }
        Toast.makeText(this,"Finish ",Toast.LENGTH_LONG).show();
    }

    private Maybe<String> getMaybe() {
        return Maybe.just("svcet");
    }

    private Single<Book> getSingleBookData() {
        return Single.just(new Book("Java programming language"));
    }

    private Single<String> getSingleObservableData() {
        return Single.just("lakpa lama");
    }

    private Observer<String> getObserver() {
        return new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                Log.i("Data are : ",""+s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.i("Complete ","success");
            }
        };
    }

    private Observable<String> getObserableData() {
        return Observable.just("C","C++","Java","Android","Swift");
    }

    private void createObservable() {

        observable=Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext(name.getText().toString());
                emitter.onComplete();
            }
        });

        observer=new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                title.setText(s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

    }
}
