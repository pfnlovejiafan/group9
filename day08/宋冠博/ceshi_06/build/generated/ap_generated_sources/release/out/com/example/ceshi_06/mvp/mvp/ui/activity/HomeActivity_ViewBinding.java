// Generated code from Butter Knife. Do not modify!
package com.example.ceshi_06.mvp.mvp.ui.activity;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.viewpager.widget.ViewPager;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.ceshi_06.R;
import com.google.android.material.tabs.TabLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HomeActivity_ViewBinding implements Unbinder {
  private HomeActivity target;

  @UiThread
  public HomeActivity_ViewBinding(HomeActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public HomeActivity_ViewBinding(HomeActivity target, View source) {
    this.target = target;

    target.mHomeActVp = Utils.findRequiredViewAsType(source, R.id.home_act_vp, "field 'mHomeActVp'", ViewPager.class);
    target.mHomeActTab = Utils.findRequiredViewAsType(source, R.id.home_act_tab, "field 'mHomeActTab'", TabLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    HomeActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mHomeActVp = null;
    target.mHomeActTab = null;
  }
}
