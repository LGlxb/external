package com.example.external.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.appcompat.widget.AppCompatImageView;

import com.example.external.R;
import com.example.external.base.BaseFragment;
import com.example.external.common.RequestCommon;
import com.example.external.mvp.bean.ConfigBean;
import com.example.external.mvp.bean.MarqueeBean;
import com.example.external.mvp.myinterface.StartInterface;
import com.example.external.mvp.network.Constant;
import com.example.external.mvp.presenter.StartPresenter;
import com.example.external.ui.activity.GetMoneyActivity;
import com.example.external.ui.activity.IdentificationActivity;
import com.example.external.utils.DialogUtils;
import com.example.external.utils.LuckyNoticeView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HomePageFragment extends BaseFragment implements StartInterface.StrartView {

    private DialogUtils utils;
    private TextView home_some_user;
    private TextView home_some_user_content;
    private LuckyNoticeView testVf;
    private List<MarqueeBean.DataBean> dataBeans = new ArrayList<>();
    @Override
    protected int getLayout() {
        return R.layout.fragment_home_page;
    }

    @Override
    protected void initView() {
        utils = new DialogUtils(mActivity, R.style.CustomDialog);
        TextView Borrow = mActivity.findViewById(R.id.Borrow);
        home_some_user = mActivity.findViewById(R.id.home_some_user);
        testVf = mActivity.findViewById(R.id.testVf);
        home_some_user_content = mActivity.findViewById(R.id.home_some_user_content);
        AppCompatImageView test = mActivity.findViewById(R.id.test);
        Borrow.setOnClickListener(v -> {
            Intent intent = new Intent(mActivity, GetMoneyActivity.class);
            startActivity(intent);
        });
        test.setOnClickListener(v -> {
            Intent intent = new Intent(mActivity, IdentificationActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void initData() {
        StartPresenter startPresenter = new StartPresenter(this);
        Map<String, Object> header = RequestCommon.getInstance().headers(mActivity);
        Map<String, Object> body = new HashMap<>();
        utils.show();
        startPresenter.get(Constant.HOMEPAGE, header, body, ConfigBean.class);
        startPresenter.get(Constant.MARQUEE_URL, header, body, MarqueeBean.class);

    }

    @Override
    protected void loadData() {

    }

    @Override
    public void success(Object data) {
        utils.dismissDialog(utils);
        if (data instanceof ConfigBean) {
            ConfigBean configBean = new ConfigBean();
            Toast.makeText(mContext, configBean.getMessage(), Toast.LENGTH_SHORT).show();
        } else if (data instanceof MarqueeBean) {
            MarqueeBean bean = (MarqueeBean) data;
            dataBeans.addAll(bean.getData());
            testVf.addNotice(dataBeans);
            testVf.startFlipping();
        }
    }

    @Override
    public void error(Object error) {
        utils.dismissDialog(utils);
    }
}