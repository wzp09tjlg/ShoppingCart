package junit.zyb.com.restaurant.util;

import android.app.Dialog;
import android.content.Context;
import android.widget.Button;
import android.widget.EditText;

import com.kpb.restaurant.R;

/**
 * Created by zhouyibo on 2017/4/18.
 */

public abstract class CustomDialog extends Dialog {
    private Context mContext;
    private EditText evid, evname, evexplain, evprice;
    private Button cancal, commit;

    public CustomDialog(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        setContentView(R.layout.dialog_goods);
        evid = (EditText) findViewById(R.id.ev_id);
        evname = (EditText) findViewById(R.id.ev_name);
        evexplain = (EditText) findViewById(R.id.ev_explain);
        evprice = (EditText) findViewById(R.id.ev_price);
        cancal = (Button) findViewById(R.id.cancal);
        commit = (Button) findViewById(R.id.commit);
    }

    public CustomDialog(Context context, int themeResId) {
        super(context, themeResId);
        init(context);
    }

    protected CustomDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public CustomDialog setid(String id){
        evid.setText(id);
        return this;
    }
    public CustomDialog setname(String id){
        evname.setText(id);
        return this;
    }
    public CustomDialog setexplain(String id){
        evexplain.setText(id);
        return this;
    }
    public CustomDialog setprice(String id){
        evprice.setText(id);
        return this;
    }
    public void cancal(){
        dismiss();
    }
    public abstract void commit();
}
