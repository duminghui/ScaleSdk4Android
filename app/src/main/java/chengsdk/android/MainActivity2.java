package chengsdk.android;

import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import com.hcoor.sdk.BodyInfo;
import com.hcoor.sdk.data.DataSdk;
import com.hcoor.sdk.data.Member_UploadSnWrapper;
import com.hcoor.sdk.data.NetRequestListener;
import com.hcoor.sdk.net.base.VolleyRequestQueue;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsMenu;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@EActivity(R.layout.activity_main_activity2)
@OptionsMenu(R.menu.menu_main_activity2)
public class MainActivity2 extends ActionBarActivity {
    private static final String TAG = "MainActivity2";

    private String accountId = "10007462";
    private String memberId = "b517c34d-cd05-4236-bde7-31f1446fcc8e";
    @AfterViews
    protected void afterViews() {
        VolleyRequestQueue.init(this);
//        DataSdk.setDebug(false);
    }

//    @Click(R.id.btnAddMemebership)
//    void btnAddMembershipClick() {
//        DataSdk.addMembership("1", 3, "nickname", 0, "2000-1-31", 160, 50, 60, "", new NetRequestListener() {
//            @Override
//            public void onNetSuccess(String json) {
//                Log.i(TAG, String.format("json:%s", json));
//            }
//
//            @Override
//            public void onError(int code, String msg) {
//                Log.i(TAG, String.format("msg:%s", msg));
//            }
//        });
//    }
//
//    @Click(R.id.btnUpdateMembership)
//    void btnUpdateMembershipClick() {
//        DataSdk.updateMembership("1", "3", "nickname", 0, "2001-1-31", 175, 60, 80, "", new NetRequestListener() {
//            @Override
//            public void onNetSuccess(String json) {
//                Log.i(TAG, String.format("json:%s", json));
//            }
//
//            @Override
//            public void onError(int code, String msg) {
//                Log.i(TAG, String.format("msg:%s", msg));
//            }
//        });
//    }
//
//    @Click(R.id.btnDelMembership)
//    void btnDelMembershipClick() {
//        DataSdk.delMembership("1", "3", new NetRequestListener() {
//            @Override
//            public void onNetSuccess(String json) {
//                Log.i(TAG, String.format("json:%s", json));
//            }
//
//            @Override
//            public void onError(int code, String msg) {
//                Log.i(TAG, String.format("msg:%s", msg));
//            }
//        });
//    }
//
//    @Click(R.id.btnMembershipList)
//    void btnMembershipListClick() {
//        DataSdk.queryMembershipList("1", new NetRequestListener() {
//            @Override
//            public void onNetSuccess(String json) {
//                Log.i(TAG, String.format("json:%s", json));
//            }
//
//            @Override
//            public void onError(int code, String msg) {
//                Log.i(TAG, String.format("msg:%s", msg));
//            }
//        });
//    }

    @Click(R.id.btnDataUpload)
    void btnDataUploadClick() {
        BodyInfo bodyInfo = new BodyInfo();
        bodyInfo.bf = 23;
        bodyInfo.weight = 50;
        bodyInfo.bmi = 2000;
        bodyInfo.muscle = 22;
        bodyInfo.bone = 21;
        bodyInfo.bodyAge = 30;
        bodyInfo.inFat = 25;
        bodyInfo.water = 30;
        bodyInfo.bmr = 1221;
        DataSdk.addMemberRecord(accountId, memberId, "", bodyInfo, new NetRequestListener() {
            @Override
            public void onNetSuccess(String json) {
                Log.i(TAG, String.format("json:%s", json));
            }

            @Override
            public void onError(int code, String msg) {
                Log.i(TAG, String.format("msg:%s", msg));
            }
        });
    }

    @Click(R.id.btnGetMemberReport)
    void btnGetMemberReport() {
        DataSdk.getMemberReport(accountId, memberId, new NetRequestListener() {
            @Override
            public void onNetSuccess(String json) {
                Log.i(TAG, String.format("json:%s", json));
            }

            @Override
            public void onError(int code, String msg) {
                Log.i(TAG, String.format("msg:%s", msg));
            }
        });
    }

    @Click(R.id.btnGetFitnessReport)
    void btnGetFitnessReport() {
        DataSdk.getFitnessReport(accountId, memberId, new NetRequestListener() {
            @Override
            public void onNetSuccess(String json) {
                Log.i(TAG, String.format("json:%s", json));
            }

            @Override
            public void onError(int code, String msg) {
                Log.i(TAG, String.format("msg:%s", msg));
            }
        });
    }

    @Click(R.id.btnGetFitnessProposal)
    void btnGetFitnessProposal() {
        DataSdk.getFitnessProposal(accountId, memberId, new NetRequestListener() {
            @Override
            public void onNetSuccess(String json) {
                Log.i(TAG, String.format("json:%s", json));
            }

            @Override
            public void onError(int code, String msg) {
                Log.i(TAG, String.format("msg:%s", msg));
            }
        });
    }

    @Click(R.id.btnGetTendencyChart)
    void btnGetTendencyChart() {
        DataSdk.getTendencyChart(accountId, memberId, "2015-2-1", "2015-2-1", new NetRequestListener() {
            @Override
            public void onNetSuccess(String json) {
                Log.i(TAG, String.format("json:%s", json));
            }

            @Override
            public void onError(int code, String msg) {
                Log.i(TAG, String.format("msg:%s", msg));
            }
        });
    }

    @Click(R.id.btnGetFamilyReport)
    void btnGetFamilyReport() {
        DataSdk.getFamilyReport(accountId, new NetRequestListener() {
            @Override
            public void onNetSuccess(String json) {
                Log.i(TAG, String.format("json:%s", json));
            }

            @Override
            public void onError(int code, String msg) {
                Log.i(TAG, String.format("msg:%s", msg));
            }
        });

    }

//    @Click(R.id.btnUploadFacelogo)
//    void btnUploadFacelogo() {
//        DataSdk.uploadFaceLogo("/storage/emulated/0/Image003.jpg", new UploadImage.OnImageUploadCallBack() {
//            @Override
//            public void uploadProgress(int rate) {
//                Log.i(TAG, String.format("rate:%s", rate));
//            }
//
//            @Override
//            public void uploadComplete(String response) {
//                Log.i(TAG, String.format("json:%s", response));
//            }
//
//            @Override
//            public void uploadFailed(int code) {
//                Log.i(TAG, String.format("uploadFailed:%s", code));
//            }
//        });
//    }

    @Click(R.id.btnUploadSN)
    void btnUploadSn() {
        Member_UploadSnWrapper wrapper = new Member_UploadSnWrapper(VolleyRequestQueue.getRequestQueue(), new NetRequestListener() {

            @Override
            public void onNetSuccess(String json) {
                Log.i(TAG, String.format("Member_UploadSnWrapper:%s", json));
            }

            @Override
            public void onError(int code, String msg) {
                Log.i(TAG, String.format("Member_UploadSnWrapper:%s,%s", code, msg));
            }
        });

        List<Member_UploadSnWrapper.SNMAC> snmacs = new ArrayList<>();
        String sn;
        String mac;
        DecimalFormat df2 = new DecimalFormat("000");
        for (int i = 1; i <= 1; i++) {
            sn = String.format("100150202%s", df2.format(i));
            mac = String.format("01:02:03:04:05:%s", String.format("%02X", i));
            snmacs.add(new Member_UploadSnWrapper.SNMAC(sn, mac));
        }

        wrapper.setValues(snmacs);
        wrapper.addRequest2Queue();
    }
}
