package com.picooc.model;

import android.content.Context;
import com.picooc.db.OperationDB;
import com.picooc.domain.ReportEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class DietAndNutritionModel
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private ArrayList<DietAndNutritionEntity> dietAndNutrtionArray;
  private DietAndNutritionEntity eat;
  private DietAndNutritionEntity notEat;
  private ArrayList<DietAndNutritionEntity> principleArray;

  public DietAndNutritionModel(Context paramContext, BodyTypeEnum paramBodyTypeEnum, int paramInt, ReportEntity paramReportEntity)
  {
    this.dietAndNutrtionArray = OperationDB.getDietAndNutritionByBodyTypeAndSex(paramContext, paramBodyTypeEnum, paramInt);
    label85: int i;
    Iterator localIterator;
    if (paramBodyTypeEnum == BodyTypeEnum.QFDLX)
    {
      this.principleArray = new ArrayList();
      if (!paramReportEntity.getStandardOrNot().booleanValue())
      {
        this.principleArray.add(new DietAndNutritionEntity("", 1, "您的肌肉量不足，在三餐中一定要保证蛋白质摄入。", 1));
        this.principleArray.add(new DietAndNutritionEntity("", 2, "为帮助身体代谢和脂肪燃烧，请保证每天2000cc的水分摄入，相当于4瓶矿泉水或8杯水。", 1));
        i = 0;
        localIterator = this.dietAndNutrtionArray.iterator();
      }
    }
    while (true)
    {
      if (!localIterator.hasNext())
      {
        return;
        this.principleArray.add(new DietAndNutritionEntity("", 1, "为保持理想体重，主食最好以高纤维的粗粮为主。", 1));
        break;
        this.principleArray = OperationDB.getPrincipleWithBodyType(paramContext, paramBodyTypeEnum, paramInt);
        break label85;
      }
      DietAndNutritionEntity localDietAndNutritionEntity = (DietAndNutritionEntity)localIterator.next();
      if (localDietAndNutritionEntity.getEat_or_not() == 1)
      {
        this.eat = localDietAndNutritionEntity;
        i++;
      }
      while (i >= 2)
      {
        return;
        this.notEat = localDietAndNutritionEntity;
        i++;
      }
    }
  }

  public DietAndNutritionEntity getEat()
  {
    return this.eat;
  }

  public DietAndNutritionEntity getNotEat()
  {
    return this.notEat;
  }

  public ArrayList<DietAndNutritionEntity> getPrincipleArray()
  {
    return this.principleArray;
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     DietAndNutritionModel
 * JD-Core Version:    0.6.2
 */
