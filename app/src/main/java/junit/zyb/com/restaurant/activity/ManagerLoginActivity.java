package junit.zyb.com.restaurant.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.kpb.restaurant.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ManagerLoginActivity extends AppCompatActivity {


    @BindView(R.id.textView2)
    TextView mTextView2;
    @BindView(R.id.et_login_phone)
    EditText mEtLoginPhone;
    @BindView(R.id.et_login_pwd)
    EditText mEtLoginPwd;
    @BindView(R.id.ib_login_visible)
    ImageButton mIbLoginVisible;
    @BindView(R.id.btn_login)
    Button mBtnLogin;

    private Context context = this;
    public static void start(Context context) {
        Intent starter = new Intent(context, ManagerLoginActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_login);
        ButterKnife.bind(this);
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ManagerActivity.start(context);
            }
        });

    }
}
