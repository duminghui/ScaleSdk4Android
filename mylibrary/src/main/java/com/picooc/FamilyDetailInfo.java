package com.picooc;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.picooc.db.OperationDB;
import com.picooc.domain.FamilyContactDetailBin;
import com.picooc.domain.InvitationInfos;
import com.picooc.internet.AsyncMessageUtils;
import com.picooc.internet.HttpUtils;
import com.picooc.internet.RequestEntity;
import com.picooc.internet.ResponseEntity;
import com.picooc.internet.http.JsonHttpResponseHandler;
import com.picooc.utils.DateUtils;
import com.picooc.utils.SharedPreferenceUtils;
import com.picooc.utils.TypefaceUtils;
import com.picooc.widget.anyncImageView.AsyncImageView;
import com.picooc.widget.loading.PicoocLoading;
import com.picooc.widget.loading.PicoocToast;
import org.achartengine.tools.ModUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class FamilyDetailInfo extends PicoocActivity
{
  private MyApplication app;
  FamilyContactDetailBin bean;
  EditText edittext;
  AsyncImageView head_image;
  private JsonHttpResponseHandler httpHandler = new JsonHttpResponseHandler()
  {
    public void onFailure(Throwable paramAnonymousThrowable, String paramAnonymousString)
    {
      PicoocLoading.dismissDialog();
      PicoocToast.showToast(FamilyDetailInfo.this, paramAnonymousString);
    }

    public void onFailure(Throwable paramAnonymousThrowable, JSONObject paramAnonymousJSONObject)
    {
      Log.i("http", "失败了:" + paramAnonymousJSONObject);
      PicoocLoading.dismissDialog();
      ResponseEntity localResponseEntity = new ResponseEntity(paramAnonymousJSONObject);
      PicoocToast.showToast(FamilyDetailInfo.this, localResponseEntity.getMessage());
      if (localResponseEntity.getMethod().equals("getMasterRoleByUserID"))
      {
        FamilyDetailInfo.this.finish();
        FamilyDetailInfo.this.overridePendingTransition(2130968594, 2130968597);
      }
    }

    public void onSuccess(int paramAnonymousInt, JSONObject paramAnonymousJSONObject)
    {
      ResponseEntity localResponseEntity = new ResponseEntity(paramAnonymousJSONObject);
      Log.i("http", "成功了:" + localResponseEntity.toString());
      String str = localResponseEntity.getMethod();
      JSONObject localJSONObject2;
      if (str.equals("getMasterRoleByUserID"))
        localJSONObject2 = localResponseEntity.getResp();
      while (true)
      {
        try
        {
          if ((localJSONObject2.has("headUrl")) && (!localJSONObject2.getString("headUrl").equals("")))
          {
            FamilyDetailInfo.this.head_image.setUrl(FamilyDetailInfo.this.app.getCurrentRole().getHead_portrait_url());
            FamilyDetailInfo.this.tv_name.setText(localJSONObject2.getString("name"));
            FamilyDetailInfo.this.tv_age.setText(DateUtils.getAge(localJSONObject2.getString("birthday"), "yyyy-MM-dd"));
            FamilyDetailInfo.this.sex = localJSONObject2.getString("gender");
            if (localJSONObject2.getString("gender").equals("1"))
            {
              FamilyDetailInfo.this.tv_age.setCompoundDrawablesWithIntrinsicBounds(2130838049, 0, 0, 0);
              PicoocLoading.dismissDialog();
            }
          }
          else if ((localJSONObject2.has("gender")) && ("1".equals(localJSONObject2.getString("gender"))))
          {
            FamilyDetailInfo.this.head_image.setImageResource(2130838457);
            continue;
          }
        }
        catch (JSONException localJSONException2)
        {
          localJSONException2.printStackTrace();
          continue;
          FamilyDetailInfo.this.head_image.setImageResource(2130838460);
          continue;
          FamilyDetailInfo.this.tv_age.setCompoundDrawablesWithIntrinsicBounds(2130838050, 0, 0, 0);
          continue;
        }
        if (str.equals("inviteRemoteRole"))
        {
          PicoocToast.showToast(FamilyDetailInfo.this, localResponseEntity.getMessage().toString());
          InvitationInfos localInvitationInfos = new InvitationInfos();
          localInvitationInfos.setUser_id(FamilyDetailInfo.this.app.getCurrentUser().getUser_id());
          long l2 = System.currentTimeMillis();
          localInvitationInfos.setTime(l2);
          Log.i("picooc", "timecurent==" + l2);
          localInvitationInfos.setFlag(1);
          label418: JSONObject localJSONObject1;
          if (FamilyDetailInfo.this.key == 2)
          {
            localInvitationInfos.setRemote_uid(FamilyDetailInfo.this.bean.getRemoteUserId());
            localInvitationInfos.setReceive_from_name(FamilyDetailInfo.this.tv_name.getText().toString());
            localInvitationInfos.setIs_already_read(0);
            localInvitationInfos.setSend_status_code(0);
            localInvitationInfos.setSend_message(FamilyDetailInfo.this.edittext.getText().toString());
            localInvitationInfos.setReceive_from_sex(FamilyDetailInfo.this.sex);
            if (!ModUtils.isNumeric(FamilyDetailInfo.this.tv_phone_number.getText().toString()))
              break label693;
            localInvitationInfos.setSend_phone(FamilyDetailInfo.this.tv_phone_number.getText().toString());
            localInvitationInfos.setSend_email("");
            label530: Intent localIntent2 = new Intent();
            FamilyDetailInfo.this.setResult(1, localIntent2);
            FamilyDetailInfo.this.finish();
            FamilyDetailInfo.this.overridePendingTransition(2130968594, 2130968597);
            localJSONObject1 = localResponseEntity.getResp();
          }
          while (true)
          {
            try
            {
              if (!localJSONObject1.has("remoteHeadUrl"))
                break label723;
              localInvitationInfos.setReceive_from_head_url(localJSONObject1.getString("remoteHeadUrl"));
              if (!localJSONObject1.getString("state").equals("3"))
                break label733;
              AsyncMessageUtils.loadBodyIndexFromServer(FamilyDetailInfo.this, localResponseEntity.getResp().getLong("remoteMainRoleId"), true);
              AsyncMessageUtils.loadRoleAndRoleInfosFromServer(FamilyDetailInfo.this, FamilyDetailInfo.this.app.getCurrentUser().getUser_id(), FamilyDetailInfo.this.httpHandler);
            }
            catch (JSONException localJSONException1)
            {
              localJSONException1.printStackTrace();
            }
            break;
            localInvitationInfos.setRemote_uid(FamilyDetailInfo.this.getIntent().getStringExtra("userID"));
            break label418;
            label693: localInvitationInfos.setSend_phone("");
            localInvitationInfos.setSend_email(FamilyDetailInfo.this.tv_phone_number.getText().toString());
            break label530;
            label723: localInvitationInfos.setReceive_from_head_url("");
          }
          label733: if (OperationDB.remoteIdIsExist(FamilyDetailInfo.this, localInvitationInfos.getUser_id(), localInvitationInfos.getRemote_uid(), "1"))
            OperationDB.updateInvitation_infos(FamilyDetailInfo.this, localInvitationInfos);
          else
            OperationDB.insertInvitation_infos(FamilyDetailInfo.this, localInvitationInfos);
        }
        else if (str.equals("getRoles"))
        {
          long l1 = ((Long)SharedPreferenceUtils.getValue(FamilyDetailInfo.this.getApplicationContext(), "user-Info", "user_id", Long.class)).longValue();
          OperationDB.updateAllRolesAndRoleInfos(FamilyDetailInfo.this, localResponseEntity.getResp(), l1);
          Intent localIntent1 = new Intent();
          localIntent1.setAction("com.picooc.invitation.refresh.ui");
          MyApplication.isUploadPhoneNu = true;
          FamilyDetailInfo.this.sendBroadcast(localIntent1);
        }
      }
    }
  };
  int key;
  TextView phoneAndEmail;
  private TextView phoneName;
  String sex = "";
  private TextView tv_age;
  private TextView tv_name;
  private TextView tv_phone_number;

  private void initWidget()
  {
    this.tv_age.setTypeface(TypefaceUtils.getTypeface(this, null));
    this.tv_phone_number.setTypeface(TypefaceUtils.getTypeface(this, null));
    this.tv_name.setTypeface(TypefaceUtils.getTypeface(this, null));
  }

  public void goNext(String paramString1, String paramString2, String paramString3)
  {
    PicoocLoading.showLoadingDialog(this);
    RequestEntity localRequestEntity = new RequestEntity("inviteRemoteRole", "5.2");
    localRequestEntity.addParam("myUserId", Long.valueOf(this.app.getCurrentUser().getUser_id()));
    localRequestEntity.addParam("romoteUserId", paramString1);
    localRequestEntity.addParam("email", paramString2);
    localRequestEntity.addParam("phone", paramString3);
    localRequestEntity.addParam("postScript", this.edittext.getText().toString());
    HttpUtils.getJson(this, localRequestEntity, this.httpHandler);
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131100359:
      finish();
      overridePendingTransition(2130968594, 2130968597);
      return;
    case 2131100431:
    }
    if (getIntent().getIntExtra("fromeKey", 0) == 2)
    {
      goNext(this.bean.getRemoteUserId(), this.bean.getEmail(), this.bean.getPhone());
      return;
    }
    goNext(getIntent().getStringExtra("userID"), "", getIntent().getStringExtra("phone"));
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903183);
    this.app = ((MyApplication)getApplicationContext());
    this.tv_age = ((TextView)findViewById(2131100427));
    this.tv_phone_number = ((TextView)findViewById(2131100429));
    this.tv_name = ((TextView)findViewById(2131099842));
    this.phoneName = ((TextView)findViewById(2131100309));
    this.head_image = ((AsyncImageView)findViewById(2131099850));
    this.edittext = ((EditText)findViewById(2131100430));
    this.phoneAndEmail = ((TextView)findViewById(2131100428));
    this.key = getIntent().getIntExtra("fromeKey", 0);
    initWidget();
    if (this.key == 2)
    {
      this.bean = ((FamilyContactDetailBin)getIntent().getSerializableExtra("bean"));
      this.phoneName.setVisibility(8);
      if ((this.bean.getHeadUrl() != null) && (!this.bean.getHeadUrl().equals("")))
      {
        this.head_image.setUrl(this.app.getCurrentRole().getHead_portrait_url());
        this.tv_name.setText(this.bean.getName());
        this.tv_age.setText(DateUtils.getAge(this.bean.getBirthday(), "yyyy-MM-dd"));
        this.sex = this.bean.getGender();
        if (!this.bean.getGender().equals("1"))
          break label395;
        this.tv_age.setCompoundDrawablesWithIntrinsicBounds(2130838049, 0, 0, 0);
      }
      while (true)
      {
        if ((this.bean.getPhone().equals("")) || (!this.bean.isSearchIsPhoneNo()))
          break label411;
        this.phoneAndEmail.setText("手机:");
        this.tv_phone_number.setText(this.bean.getPhone());
        return;
        if ((this.bean.getGender() != null) && ("1".equals(this.bean.getGender())))
        {
          this.head_image.setImageResource(2130838457);
          break;
        }
        this.head_image.setImageResource(2130838460);
        break;
        label395: this.tv_age.setCompoundDrawablesWithIntrinsicBounds(2130838050, 0, 0, 0);
      }
      label411: this.phoneAndEmail.setText("邮箱:");
      this.tv_phone_number.setText(this.bean.getEmail());
      return;
    }
    PicoocLoading.showLoadingDialog(this);
    RequestEntity localRequestEntity = new RequestEntity("getMasterRoleByUserID", "5.2");
    localRequestEntity.addParam("userID", getIntent().getStringExtra("userID"));
    HttpUtils.getJson(this, localRequestEntity, this.httpHandler);
    this.tv_phone_number.setText(getIntent().getStringExtra("phone"));
    this.phoneName.setText(getIntent().getStringExtra("phoneName"));
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
    {
      finish();
      overridePendingTransition(2130968594, 2130968597);
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     FamilyDetailInfo
 * JD-Core Version:    0.6.2
 */
