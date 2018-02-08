package empolyesecurity.testmvp.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

import javax.inject.Inject;

import butterknife.ButterKnife;
import empolyesecurity.testmvp.R;
import empolyesecurity.testmvp.ui.base.BaseActivity;

import empolyesecurity.testmvp.ui.blogs.BlogActivity;

import empolyesecurity.testmvp.ui.login.LoginActivity;

public class SplashActivity extends BaseActivity implements SplashMvpView {
    @Inject
    SplashMvpPresenter<SplashMvpView> mPresenter;
    private static int SPLASH_TIME_OUT = 3000;

    static Integer[] arr = {2, 6, 77, 44, 21, 56, 23, 11};
    static int first = arr[0];
    static int second = arr[0];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(SplashActivity.this);
        BubbleSort ob = new BubbleSort();
        int arr[] = {64, 34, 25, 12, 22, 11, 90};
        ob. bubble_srt(arr);
        System.out.println("Sorted array");
        //ob.printArray(arr);
    }


    @Override
    public void openLoginActivity() {
        Intent intent = LoginActivity.getStartIntent(SplashActivity.this);
        startActivity(intent);
        finish();

    }

    @Override
    public void openMainActivity() {
       /* Intent i = new Intent(SplashActivity.this,BlogActivity.class);
        startActivity(i);
        finish();*/
        int n = 10;
        printTriagle(n);
        new Handler().postDelayed(new Runnable() {


            /* * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company*/


            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
              /*  Intent i = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(i);*/

                // close this activity
                finish();
                Intent i = new Intent(SplashActivity.this, BlogActivity.class);
                startActivity(i);
                finish();
                System.out.println("11111111111111111111");

            }
        }, SPLASH_TIME_OUT);

    }

    @Override
    public void startSyncService() {

    }

    public static void printTriagle(int n) {
        // number of spaces


        int k = n;

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < k; j++) {
                System.out.print(" ");
            }
            k = k - 1;
            for (int j = 0; j <= i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }


        ArrayList<Integer> val = new ArrayList<Integer>(Arrays.asList(arr));

       /* for(int jj= 0;jj<arr.length;jj++){
            val.add(jj);
        }*/

      /*  int fi=val.get(0);
        int si=val.get(0);
        for(int i=0;i<val.size();i++) {
            if (val.get(i) > fi) {
                si = fi;
                fi = val.get(i);

            } else if (val.get(i) > si) {
                si = val.get(i);

            }
        }*/
       /* for(int i=0;i<arr.length;i++){
            if (arr[i] > first) {
                second = first;
                first = arr[i];

            } else if (arr[i] > second) {
                second = arr[i];

            }
           *//* if(arr[i]>first){
                second=first;
                first=arr[i];

            }else if(arr[i]>second){
                second=arr[i];

            }*//*

        }*/
        // System.out.println("99999999999"+fi);
        //   System.out.println("%%%%%%%%%%%%%"+si);


     /*   int k = 2 * n - 2;
        System.out.println("5555555555555"+k);
        String temp = "";
*//*        for(int i=0; i<n; i++)
        {
            String t = "";
            for(int j=0; j<=i; j++)
            {
                t = t + "*";
            }
            temp = temp + t + "\n";
        }
      /*//*//*  //System.out.println("88888888888888"+temp);



        // outer loop to handle number of rows
        //  n in this case
        for (int i = 0; i < n; i++) {

            // inner loop to handle number spaces
            // values changing acc. to requirement
            for (int j = 0; j < k; j++) {
                // printing spaces
                System.out.print(" ");
            }

            // decrementing k after each loop
            k = k - 1;

            //  inner loop to handle number of columns
            //  values changing acc. to outer loop
            for (int j = 0; j <= i; j++) {
                // printing stars
                System.out.print("* ");
            }

            // ending line after each row
            System.out.println();
        }*/
    }

    @Override
    protected void setUp() {

    }

    class BubbleSort {
        public  void bubble_srt(int array[]) {
            int n = array.length;
            int k;
            for (int m = n; m >= 0; m--) {
                for (int i = 0; i < n - 1; i++) {
                    k = i + 1;
                    if (array[i] > array[k]) {
                        swapNumbers(i, k, array);
                        System.out.println("444444444444444");
                    }
                }
                printNumbers(array);
            }
        }
        private  void swapNumbers(int i, int j, int[] array) {

            int temp;
            temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }

          /*void bubble_srt(int array[]) {
            int n = array.length;
            int k;
            for (int m = n; m >= 0; m--) {
                for (int i = 0; i < n - 1; i++) {
                    k = i + 1;
                    if (array[i] > array[k]) {
                        swapNumbers(i, k, array);
                    }
                }
                printNumbers(array);
            }
        }*/
        private  void printNumbers(int[] input) {

            for (int i = 0; i < input.length; i++) {
                System.out.print(input[i] + ", ");
            }
            System.out.println("\n");
        }

    }
}
