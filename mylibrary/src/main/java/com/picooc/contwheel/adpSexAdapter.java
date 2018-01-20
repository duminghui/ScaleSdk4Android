package com.picooc.contwheel;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import org.achartengine.tools.ModUtils;

public class adpSexAdapter extends adpAbstractWheelTextAdapter
{
  public static final int[] FLAGS = { 2130837963, 2130837963, 2130837963 };
  public static final String[] SEXS = { "1934", "1935", "1936", "1937", "1938", "1939", "1940", "1941", "1942", "1943", "1944", "1945", "1946", "1947", "1948", "1949", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004" };

  public adpSexAdapter(Context paramContext)
  {
    super(paramContext, 2130903127, 0);
    setItemTextResource(2131100083);
  }

  public View getItem(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    View localView = super.getItem(paramInt, paramView, paramViewGroup);
    TextView localTextView = (TextView)localView.findViewById(2131100083);
    localTextView.setTypeface(ModUtils.getTypeface(this.context));
    localTextView.setTextSize(23.0F);
    return localView;
  }

  public CharSequence getItemText(int paramInt)
  {
    return SEXS[paramInt];
  }

  public int getItemsCount()
  {
    return SEXS.length;
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     adpSexAdapter
 * JD-Core Version:    0.6.2
 */
