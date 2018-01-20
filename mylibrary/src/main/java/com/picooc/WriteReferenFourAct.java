package com.picooc;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class WriteReferenFourAct extends PicoocActivity
{
  public void invit()
  {
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131100335:
    }
    startActivity(new Intent(this, PicoocActivity3.class));
    finish();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903228);
    ImageView localImageView = (ImageView)findViewById(2131100335);
    AnimationDrawable localAnimationDrawable = (AnimationDrawable)getResources().getDrawable(2130968583);
    localImageView.setImageDrawable(localAnimationDrawable);
    localAnimationDrawable.start();
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     WriteReferenFourAct
 * JD-Core Version:    0.6.2
 */
