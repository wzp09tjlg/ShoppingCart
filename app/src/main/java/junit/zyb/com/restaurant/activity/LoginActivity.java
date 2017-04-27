package junit.zyb.com.restaurant.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.kpb.restaurant.R;

import junit.zyb.com.restaurant.util.SRPreferences;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.et_login_phone)
    EditText mEtLoginPhone;
    @BindView(R.id.et_login_pwd)
    EditText mEtLoginPwd;
    @BindView(R.id.ib_login_visible)
    ImageButton mIbLoginVisible;
    @BindView(R.id.btn_login)
    Button mBtnLogin;
    @BindView(R.id.tv_login_register)
    TextView mTvLoginRegister;
    @BindView(R.id.tv_login_forget_pwd)
    TextView mTvLoginForgetPwd;
    private Context mContext = this;
    private boolean mbDisplayFlg = false;

    public static void start(Context context) {
        Intent starter = new Intent(context, LoginActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.et_login_phone, R.id.et_login_pwd, R.id.ib_login_visible, R.id.btn_login, R.id.tv_login_register, R.id.tv_login_forget_pwd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_login_visible:
                if (!mbDisplayFlg) {
                    mEtLoginPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    mEtLoginPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                mbDisplayFlg = !mbDisplayFlg;
                mEtLoginPwd.postInvalidate();
                break;
            case R.id.btn_login:
                String phone = mEtLoginPhone.getText().toString();
                String password = mEtLoginPwd.getText().toString();
                SRPreferences.getInstance().setString(SRPreferences.SRP_USER_PHONE,phone);
                SRPreferences.getInstance().setString(SRPreferences.SRP_USER_PASSWORD,password);
//                OkHttpUtils.get().url(Constants.URL_LOGIN).build().execute(new StringCallback() {
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//                        //登陆失败
//                    }
//
//                    @Override
//                    public void onResponse(String response, int id) {
//                        //登陆成功
//                        MainActivity.start(mContext);
//                    }
//                });
                MainActivity.start(mContext);
                finish();
                break;
            case R.id.tv_login_register:
                RegisterActivity.start(mContext);
                finish();
                break;
            case R.id.tv_login_forget_pwd:
                RegisterActivity.start(mContext);
                finish();
                break;
        }
    }
}
