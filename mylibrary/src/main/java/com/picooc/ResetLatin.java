package com.picooc;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.picooc.utils.SharedPreferenceUtils;
import org.achartengine.tools.ModUtils;

@SuppressLint({"ValidFragment"})
public class ResetLatin
{
  private ResetLatinActivity mActivity;
  private Typeface mTypeface;

  public ResetLatin(ResetLatinActivity paramResetLatinActivity)
  {
    this.mActivity = paramResetLatinActivity;
    this.mTypeface = ModUtils.getTypeface(paramResetLatinActivity);
  }

  static AnimationDrawable createAnimationDrawable(Context paramContext)
  {
    AnimationDrawable localAnimationDrawable = new AnimationDrawable();
    Drawable localDrawable1 = paramContext.getResources().getDrawable(2130837694);
    Drawable localDrawable2 = paramContext.getResources().getDrawable(2130837695);
    localAnimationDrawable.addFrame(localDrawable1, 800);
    localAnimationDrawable.addFrame(localDrawable2, 800);
    localAnimationDrawable.setOneShot(false);
    return localAnimationDrawable;
  }

  public void dissmiss(Fragment paramFragment)
  {
    FragmentTransaction localFragmentTransaction = this.mActivity.getSupportFragmentManager().beginTransaction();
    localFragmentTransaction.remove(paramFragment);
    localFragmentTransaction.commit();
  }

  public boolean onBackPressed()
  {
    return true;
  }

  public void replace(Fragment paramFragment)
  {
    FragmentTransaction localFragmentTransaction = this.mActivity.getSupportFragmentManager().beginTransaction();
    localFragmentTransaction.replace(2131100063, paramFragment);
    localFragmentTransaction.addToBackStack(null);
    localFragmentTransaction.commit();
  }

  public void show()
  {
    FragmentTransaction localFragmentTransaction = this.mActivity.getSupportFragmentManager().beginTransaction();
    localFragmentTransaction.add(2131100063, new ResetLatinOne(null));
    localFragmentTransaction.commit();
  }

  class AniListenerFactory
    implements Animation.AnimationListener
  {
    private AnimationDrawable ad;
    private Animation ani;
    private View view;

    AniListenerFactory(View paramAnimation, Animation arg3)
    {
      this.view = paramAnimation;
      Object localObject;
      this.ani = localObject;
    }

    AniListenerFactory(View paramAnimation, Animation paramAnimationDrawable, AnimationDrawable arg4)
    {
      this.view = paramAnimation;
      this.ani = paramAnimationDrawable;
      Object localObject;
      this.ad = localObject;
    }

    public void onAnimationEnd(Animation paramAnimation)
    {
      if (this.ad != null)
      {
        if (!this.ad.isRunning())
          break label43;
        this.ad.stop();
      }
      while (true)
      {
        if (this.view != null)
          this.view.startAnimation(this.ani);
        return;
        label43: this.ad.start();
      }
    }

    public void onAnimationRepeat(Animation paramAnimation)
    {
    }

    public void onAnimationStart(Animation paramAnimation)
    {
    }
  }

  private class ResetLatinOne extends Fragment
  {
    private ResetLatinOne()
    {
    }

    public void onCreate(Bundle paramBundle)
    {
      super.onCreate(paramBundle);
    }

    public Animation onCreateAnimation(int paramInt1, boolean paramBoolean, int paramInt2)
    {
      AccelerateDecelerateInterpolator localAccelerateDecelerateInterpolator = new AccelerateDecelerateInterpolator();
      if (!paramBoolean)
        return AnimCreator.createTranslateOut(getActivity(), localAccelerateDecelerateInterpolator);
      return null;
    }

    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
    {
      View localView = paramLayoutInflater.inflate(2130903070, paramViewGroup, false);
      ImageView localImageView1 = (ImageView)localView.findViewById(2131099813);
      Button localButton = (Button)localView.findViewById(2131099816);
      ImageView localImageView2 = (ImageView)localView.findViewById(2131099814);
      TextView localTextView = (TextView)localView.findViewById(2131099815);
      ((TextView)localView.findViewById(2131099811)).setTypeface(ResetLatin.this.mTypeface);
      ((TextView)localView.findViewById(2131099812)).setTypeface(ResetLatin.this.mTypeface);
      localTextView.setTypeface(ResetLatin.this.mTypeface);
      localButton.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          ResetLatin.this.replace(new ResetLatinTwo(ResetLatin.this, null));
        }
      });
      AnimationDrawable localAnimationDrawable = ResetLatin.createAnimationDrawable(getActivity());
      localImageView2.setBackgroundDrawable(localAnimationDrawable);
      ScaleAnimation localScaleAnimation1 = AnimCreator.createScaleAnimationLeftCorner();
      TranslateAnimation localTranslateAnimation = AnimCreator.createTranslateAnimation();
      ScaleAnimation localScaleAnimation2 = AnimCreator.createScaleAnimationCenter();
      AlphaAnimation localAlphaAnimation = AnimCreator.createAlphaAnimationIn();
      localScaleAnimation2.setAnimationListener(new AniListenerFactory(ResetLatin.this, localButton, localAlphaAnimation));
      localTranslateAnimation.setAnimationListener(new AniListenerFactory(ResetLatin.this, localImageView2, localScaleAnimation1));
      localScaleAnimation1.setAnimationListener(new AniListenerFactory(ResetLatin.this, localTextView, localScaleAnimation2, localAnimationDrawable));
      localImageView1.startAnimation(localTranslateAnimation);
      return localView;
    }
  }

  private class ResetLatinThree extends Fragment
  {
    private View.OnClickListener l = new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        ResetLatin.this.mActivity.reSetBlockEvent(false);
        ResetLatin.this.dissmiss(ResetLatinThree.this);
        new Handler().postDelayed(new Runnable()
        {
          public void run()
          {
            ResetLatin.this.mActivity.finish();
            Intent localIntent = new Intent();
            localIntent.setAction("com.picooc.i.kown");
            ResetLatinThree.this.getActivity().sendBroadcast(localIntent);
          }
        }
        , 100L);
      }
    };

    private ResetLatinThree()
    {
    }

    public void onCreate(Bundle paramBundle)
    {
      super.onCreate(paramBundle);
    }

    public Animation onCreateAnimation(int paramInt1, boolean paramBoolean, int paramInt2)
    {
      AccelerateDecelerateInterpolator localAccelerateDecelerateInterpolator = new AccelerateDecelerateInterpolator();
      if (!paramBoolean)
        return null;
      return AnimCreator.createTranslateIn(getActivity(), localAccelerateDecelerateInterpolator);
    }

    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
    {
      View localView = paramLayoutInflater.inflate(2130903071, paramViewGroup, false);
      ((TextView)localView.findViewById(2131099817)).setTypeface(ResetLatin.this.mTypeface);
      ((TextView)localView.findViewById(2131099818)).setTypeface(ResetLatin.this.mTypeface);
      localView.setOnClickListener(this.l);
      localView.findViewById(2131099819).setOnClickListener(this.l);
      return localView;
    }
  }

  private class ResetLatinTwo extends Fragment
  {
    int i;

    private ResetLatinTwo()
    {
    }

    private AnimationSet getAnimationSet(Animation.AnimationListener paramAnimationListener)
    {
      AnimationSet localAnimationSet = new AnimationSet(true);
      ScaleAnimation localScaleAnimation = new ScaleAnimation(1.0F, 2.5F, 1.0F, 2.5F, 1, 0.5F, 1, 0.5F);
      localScaleAnimation.setAnimationListener(paramAnimationListener);
      localAnimationSet.addAnimation(localScaleAnimation);
      localAnimationSet.addAnimation(new AlphaAnimation(1.0F, 0.0F));
      localAnimationSet.setDuration(1000L);
      localAnimationSet.setFillAfter(false);
      return localAnimationSet;
    }

    public void onCreate(Bundle paramBundle)
    {
      super.onCreate(paramBundle);
    }

    public Animation onCreateAnimation(int paramInt1, boolean paramBoolean, int paramInt2)
    {
      AccelerateDecelerateInterpolator localAccelerateDecelerateInterpolator = new AccelerateDecelerateInterpolator();
      if (!paramBoolean)
        return AnimCreator.createTranslateOut(getActivity(), localAccelerateDecelerateInterpolator);
      return AnimCreator.createTranslateIn(getActivity(), localAccelerateDecelerateInterpolator);
    }

    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
    {
      View localView = paramLayoutInflater.inflate(2130903072, paramViewGroup, false);
      final TextView localTextView = (TextView)localView.findViewById(2131099821);
      localTextView.setTypeface(ResetLatin.this.mTypeface);
      ((TextView)localView.findViewById(2131099820)).setTypeface(ResetLatin.this.mTypeface);
      AnimationSet localAnimationSet = getAnimationSet(new Animation.AnimationListener()
      {
        public void onAnimationEnd(Animation paramAnonymousAnimation)
        {
          int i = Integer.parseInt(localTextView.getText().toString());
          if (i == 1)
          {
            ResetLatin.this.replace(new ResetLatinThree(ResetLatin.this, null));
            localTextView.setVisibility(8);
            SharedPreferenceUtils.resetLatin(ResetLatinTwo.this.getActivity(), false);
          }
          int j;
          do
          {
            return;
            j = i - 1;
            localTextView.setText(j);
            localTextView.getAnimation().setStartOffset(0L);
            localTextView.getAnimation().setAnimationListener(this);
            localTextView.startAnimation(localTextView.getAnimation());
          }
          while (j != 0);
          localTextView.setVisibility(8);
        }

        public void onAnimationRepeat(Animation paramAnonymousAnimation)
        {
        }

        public void onAnimationStart(Animation paramAnonymousAnimation)
        {
        }
      });
      localAnimationSet.setStartOffset(2000L);
      localTextView.startAnimation(localAnimationSet);
      return localView;
    }
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     ResetLatin
 * JD-Core Version:    0.6.2
 */
