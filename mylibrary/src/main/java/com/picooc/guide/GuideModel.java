package com.picooc.guide;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class GuideModel
  implements Serializable, Parcelable
{
  public static final Parcelable.Creator<GuideModel> CREATOR = new Parcelable.Creator()
  {
    public GuideModel createFromParcel(Parcel paramAnonymousParcel)
    {
      GuideModel localGuideModel = new GuideModel();
      localGuideModel.setX(paramAnonymousParcel.readInt());
      localGuideModel.setY(paramAnonymousParcel.readInt());
      Bitmap localBitmap = (Bitmap)Bitmap.CREATOR.createFromParcel(paramAnonymousParcel);
      if (localBitmap != null)
        localGuideModel.setBitmap(localBitmap);
      return localGuideModel;
    }

    public GuideModel[] newArray(int paramAnonymousInt)
    {
      return new GuideModel[paramAnonymousInt];
    }
  };
  private static final long serialVersionUID = -2452423952874142606L;
  public int X;
  public int Y;
  public Bitmap bitmap;

  public GuideModel()
  {
  }

  public GuideModel(Bitmap paramBitmap, int paramInt1, int paramInt2)
  {
    this.bitmap = paramBitmap;
    this.X = paramInt1;
    this.Y = paramInt2;
  }

  public int describeContents()
  {
    return 0;
  }

  public Bitmap getBitmap()
  {
    return this.bitmap;
  }

  public int getX()
  {
    return this.X;
  }

  public int getY()
  {
    return this.Y;
  }

  public void setBitmap(Bitmap paramBitmap)
  {
    this.bitmap = paramBitmap;
  }

  public void setX(int paramInt)
  {
    this.X = paramInt;
  }

  public void setY(int paramInt)
  {
    this.Y = paramInt;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeInt(this.X);
    paramParcel.writeInt(this.Y);
    if (this.bitmap != null)
      this.bitmap.writeToParcel(paramParcel, 0);
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     GuideModel
 * JD-Core Version:    0.6.2
 */
