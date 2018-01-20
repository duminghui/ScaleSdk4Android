package demo.scaletester;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import com.hcoor.sdk.BluetoothControl;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;


@EActivity(R.layout.activity_main)
public class MainActivity extends ActionBarActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean btIsOpen = BluetoothControl.btIsOpen();
        Log.d(TAG, String.format("btIsOpe:%s", btIsOpen));
        boolean operateSuccess = false;
        if (!btIsOpen) {
            operateSuccess = BluetoothControl.openBT();
        }
        Log.d(TAG, String.format("operateSuccess:%s", operateSuccess));
    }

    @AfterViews
    protected void afterViews() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, OperateFragment_.builder().build())
                .commit();
    }


}
