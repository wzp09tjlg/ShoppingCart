package junit.zyb.com.restaurant.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kpb.restaurant.R;

import junit.zyb.com.restaurant.activity.AboutActivity;
import junit.zyb.com.restaurant.activity.BuyRecordActivity;
import junit.zyb.com.restaurant.activity.UsreinfoActivity;
import junit.zyb.com.restaurant.util.SRPreferences;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class UserFragment extends BaseFragment {

    @BindView(R.id.tv_name_user)
    TextView mTvNameUser;
    @BindView(R.id.layout_parent)
    RelativeLayout mLayoutParent;
    @BindView(R.id.label_iv_left)
    ImageView mLabelIvLeft;
    @BindView(R.id.layout_userinfo)
    LinearLayout mLayoutUserinfo;
    @BindView(R.id.layout_buy)
    LinearLayout mLayoutBuy;
    @BindView(R.id.label_tv_center)
    TextView mLabelTvCenter;
    @BindView(R.id.layout_about)
    LinearLayout mLayoutAbout;
    Unbinder unbinder;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_user, null);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        mTvNameUser.setText(SRPreferences.getInstance().getString(SRPreferences.SRP_USER_PHONE));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.layout_userinfo, R.id.layout_buy, R.id.label_tv_center})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_userinfo:
                UsreinfoActivity.start(getContext());
                break;
            case R.id.layout_buy:
                BuyRecordActivity.start(getContext());
                break;
            case R.id.label_tv_center:
                AboutActivity.start(getContext());
                break;
        }
    }
}
