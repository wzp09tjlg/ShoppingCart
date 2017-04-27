package junit.zyb.com.restaurant.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.kpb.restaurant.R;

import junit.zyb.com.restaurant.util.SRPreferences;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity {


    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.register_phone)
    EditText mRegisterPhone;
    @BindView(R.id.register_code)
    EditText mRegisterCode;
    @BindView(R.id.register_getcode)
    Button mRegisterGetcode;
    @BindView(R.id.register_name)
    EditText mRegisterName;
    @BindView(R.id.radioMale)
    RadioButton mRadioMale;
    @BindView(R.id.radioFemale)
    RadioButton mRadioFemale;
    @BindView(R.id.register_sex)
    RadioGroup mRegisterSex;
    @BindView(R.id.register_password)
    EditText mRegisterPassword;
    @BindView(R.id.register_password_again)
    EditText mRegisterPasswordAgain;
    @BindView(R.id.registerbtn)
    Button mRegisterbtn;
    @BindView(R.id.register_textview_warning)
    TextView mRegisterTextviewWarning;
    @BindView(R.id.register_textview_protocol)
    TextView mRegisterTextviewProtocol;
    @BindView(R.id.layout_bottom)
    LinearLayout mLayoutBottom;
    @BindView(R.id.tv_title)
    TextView mTvTitle;

    private Context mContext = this;
    private String name = "";
    private String phone = "";
    private String password = "";
    private String passwordAgain = "";


    public static void start(Context context, boolean reg) {
        Intent starter = new Intent(context, RegisterActivity.class);
        starter.putExtra("reg", reg);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        mRegisterbtn.setText(getIntent().getExtras().getBoolean("reg") ? "注册" : "提交");
        mLayoutBottom.setVisibility(getIntent().getExtras().getBoolean("reg") ? View.VISIBLE : View.GONE);
        mTvTitle.setText(getIntent().getExtras().getBoolean("reg") ? "注册" : "修改");
        mRegisterPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                phone = s.toString();
            }
        });
        mRegisterName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                name = s.toString();
            }
        });
        mRegisterSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                SRPreferences.getInstance().setString(SRPreferences.SRP_USER_SEX, checkedId == 0 ? "man" : "woman");
            }
        });
        mRegisterPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                password = s.toString();
            }
        });
        mRegisterPasswordAgain.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                passwordAgain = s.toString();
            }
        });
    }

    public static void start(Context context) {
        start(context, true);
    }


    @OnClick({R.id.back, R.id.register_phone, R.id.register_code, R.id.register_getcode, R.id.register_name, R.id.radioMale, R.id.radioFemale, R.id.register_sex, R.id.register_password, R.id.register_password_again, R.id.registerbtn, R.id.register_textview_warning, R.id.register_textview_protocol})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                break;
            case R.id.register_phone:
                break;
            case R.id.register_code:
                break;
            case R.id.register_getcode:
                break;
            case R.id.register_name:
                break;
            case R.id.radioMale:
                break;
            case R.id.radioFemale:
                break;
            case R.id.register_sex:
                break;
            case R.id.register_password:
                break;
            case R.id.register_password_again:
                break;
            case R.id.registerbtn:
                if (!name.equals("") && !phone.equals("") && password.equals(passwordAgain) && !password.equals("")) {
                    SRPreferences.getInstance().setString(SRPreferences.SRP_USER_PHONE, phone);
                    SRPreferences.getInstance().setString(SRPreferences.SRP_USER_USERNAME, name);
                    SRPreferences.getInstance().setString(SRPreferences.SRP_USER_PASSWORD, password);
                    Toast.makeText(mContext, "注册成功", Toast.LENGTH_SHORT).show();
                    MainActivity.start(mContext);
                } else {
                    Toast.makeText(mContext, "注册失败，请检查", Toast.LENGTH_SHORT).show();
                }
                finish();
                break;
            case R.id.register_textview_warning:
                break;
            case R.id.register_textview_protocol:
                break;
        }
    }
}
