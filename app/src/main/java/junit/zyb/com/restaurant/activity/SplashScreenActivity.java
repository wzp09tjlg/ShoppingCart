package junit.zyb.com.restaurant.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.kpb.restaurant.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SplashScreenActivity extends AppCompatActivity {

    @BindView(R.id.bt_login)
    Button mBtLogin;
    @BindView(R.id.bt_register)
    Button mBtRegister;
    private Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_login, R.id.bt_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_login:
                LoginActivity.start(mContext);
                finish();
                break;
            case R.id.bt_register:
                RegisterActivity.start(mContext);
                finish();
                break;
        }
    }
}
