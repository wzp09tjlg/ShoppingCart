package junit.zyb.com.restaurant.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kpb.restaurant.R;

import junit.zyb.com.restaurant.util.SRPreferences;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.kpb.restaurant.R.id.userinfo_name;

public class UsreinfoActivity extends AppCompatActivity {

    @BindView(R.id.tv_change)
    TextView mTvChange;
    @BindView(userinfo_name)
    TextView mUserinfoName;
    @BindView(R.id.userinfo_sex)
    TextView mUserinfoSex;
    @BindView(R.id.userinfo_phone)
    TextView mUserinfoPhone;
    @BindView(R.id.userinfo_num)
    TextView mUserinfoNum;
    @BindView(R.id.userinfo_address)
    TextView mUserinfoAddress;
    @BindView(R.id.activity_usreinfo)
    LinearLayout mActivityUsreinfo;
    @BindView(R.id.bt_manager_login)
    Button mBtManagerLogin;

    private Context mContext = this;

    public static void start(Context context) {
        Intent starter = new Intent(context, UsreinfoActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usreinfo);
        ButterKnife.bind(this);

        mUserinfoName.setText(SRPreferences.getInstance().getString(SRPreferences.SRP_USER_USERNAME));
        mUserinfoSex.setText(SRPreferences.getInstance().getString(SRPreferences.SRP_USER_SEX));
        mUserinfoPhone.setText(SRPreferences.getInstance().getString(SRPreferences.SRP_USER_PHONE));

        mTvChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterActivity.start(mContext, false);
            }
        });
    }

    @OnClick(R.id.bt_manager_login)
    public void onViewClicked() {
        ManagerLoginActivity.start(mContext);
    }
}
