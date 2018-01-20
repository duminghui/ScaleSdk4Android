package com.picooc;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;
import com.picooc.adapter.GalleryAdapter;
import java.util.ArrayList;
import java.util.List;
import org.achartengine.tools.ModUtils;

public class LiftMakeWeitTotalAct extends PicoocActivity
  implements AdapterView.OnItemSelectedListener, View.OnClickListener
{
  private static final long DELAY = 100L;
  private static final int MSG_ZOOM_IN = 1;
  GalleryAdapter adpter;
  private Gallery gallery;
  private int key = 0;
  private List<String> list;
  Handler mGalleryHandler = new Handler()
  {
    public void dispatchMessage(Message paramAnonymousMessage)
    {
      if (paramAnonymousMessage.what == 1)
        LiftMakeWeitTotalAct.this.text.setText((CharSequence)LiftMakeWeitTotalAct.this.list.get(paramAnonymousMessage.arg1));
    }
  };
  View mPrev;
  TextView text;

  private ImageView setVisible(int paramInt)
  {
    return null;
  }

  public void finish()
  {
    super.finish();
  }

  public void getlist()
  {
    this.list = new ArrayList();
    double d = 39.5D;
    for (int i = 0; ; i++)
    {
      if (i >= 220)
        return;
      d += 0.5D;
      this.list.add(ModUtils.subZeroAndDot(d));
    }
  }

  public void invit()
  {
    this.gallery = ((Gallery)findViewById(2131100564));
    this.gallery.setCallbackDuringFling(false);
    this.text = ((TextView)findViewById(2131100563));
    this.adpter = new GalleryAdapter(this, this.list);
    this.gallery.setAdapter(this.adpter);
    this.gallery.setSelection(this.gallery.getCount() / 2);
    this.gallery.setOnItemSelectedListener(this);
    ImageView localImageView1 = (ImageView)findViewById(2131099651);
    ImageView localImageView2 = (ImageView)findViewById(2131099650);
    localImageView1.setImageResource(2130838428);
    localImageView1.setOnClickListener(this);
    localImageView1.setVisibility(8);
    localImageView2.setOnClickListener(this);
    ((TextView)findViewById(2131099699)).setText("设定目标");
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    case 2131099651:
    default:
      return;
    case 2131099650:
    }
    finish();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903206);
    getlist();
    invit();
  }

  public void onItemSelected(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    this.mPrev = paramAdapterView;
    this.mGalleryHandler.removeCallbacksAndMessages(paramAdapterView);
    Message localMessage = Message.obtain(this.mGalleryHandler, 1, paramAdapterView);
    localMessage.arg1 = paramInt;
    this.mGalleryHandler.sendMessageDelayed(localMessage, 100L);
  }

  public void onNothingSelected(AdapterView<?> paramAdapterView)
  {
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     LiftMakeWeitTotalAct
 * JD-Core Version:    0.6.2
 */
