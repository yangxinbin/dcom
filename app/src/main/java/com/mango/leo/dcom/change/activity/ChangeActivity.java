package com.mango.leo.dcom.change.activity;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mango.leo.dcom.DcomActivity;
import com.mango.leo.dcom.R;
import com.mango.leo.dcom.base.BaseActivity;
import com.mango.leo.dcom.change.bean.MethodBeans;
import com.mango.leo.dcom.change.bean.RevertBeans;
import com.mango.leo.dcom.util.ViewPageAdapter;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.luck.picture.lib.tools.ScreenUtils.dip2px;

public class ChangeActivity extends BaseActivity {
    @Bind(R.id.imageView_back)
    ImageView imageViewBack;
    @Bind(R.id.imageView_add)
    ImageView imageViewAdd;
    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.viewPager)
    ViewPager viewPager;
    private ArrayList<String> mDatas;
    List<Fragment> mfragments = new ArrayList<Fragment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);
        ButterKnife.bind(this);
        initDatas();
        init();
    }

    private void initDatas() {
        //  mDatas = new ArrayList<String>(Arrays.asList("       我的事件       ", "       全部事件       "));
        mDatas = new ArrayList<String>(Arrays.asList("草稿箱","我的变更", "全部变更"));

    }

    private void init() {
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        ViewPageAdapter vp = new ViewPageAdapter(getSupportFragmentManager(), mfragments, mDatas);
        tabLayout.setupWithViewPager(viewPager);
        mfragments.add(new DraftChangeFragment());
        mfragments.add(new MyChangeFragment());
        mfragments.add(new AllChangeFragment());
        viewPager.setAdapter(vp);
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(1);
        reflex(tabLayout);
    }

    public void reflex(final TabLayout tabLayout) {
        //了解源码得知 线的宽度是根据 tabView的宽度来设置的
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                try {
                    //拿到tabLayout的mTabStrip属性
                    LinearLayout mTabStrip = (LinearLayout) tabLayout.getChildAt(0);

                    int dp20 = dip2px(tabLayout.getContext(), 20);

                    for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                        View tabView = mTabStrip.getChildAt(i);

                        //拿到tabView的mTextView属性  tab的字数不固定一定用反射取mTextView
                        Field mTextViewField = tabView.getClass().getDeclaredField("mTextView");
                        mTextViewField.setAccessible(true);

                        TextView mTextView = (TextView) mTextViewField.get(tabView);

                        tabView.setPadding(0, 0, 0, 0);

                        //因为我想要的效果是   字多宽线就多宽，所以测量mTextView的宽度
                        int width = 0;
                        width = mTextView.getWidth();
                        if (width == 0) {
                            mTextView.measure(0, 0);
                            width = mTextView.getMeasuredWidth();
                        }

                        //设置tab左右间距为10dp  注意这里不能使用Padding 因为源码中线的宽度是根据 tabView的宽度来设置的
                        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView.getLayoutParams();
                        params.width = width;
                        params.leftMargin = dp20;
                        params.rightMargin = dp20;
                        tabView.setLayoutParams(params);

                        tabView.invalidate();
                    }

                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @OnClick({R.id.imageView_back, R.id.imageView_add})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.imageView_back:
                intent = new Intent(this, DcomActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.imageView_add:
                EventBus.getDefault().removeStickyEvent(MethodBeans.class);
                EventBus.getDefault().removeStickyEvent(RevertBeans.class);
                intent = new Intent(this, AddChangeActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Log.v("yyyyy", "eee::::");

            Intent intent = new Intent(this, DcomActivity.class);
            startActivity(intent);
            finish();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

}
