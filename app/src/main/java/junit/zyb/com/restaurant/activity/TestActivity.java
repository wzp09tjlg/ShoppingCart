package junit.zyb.com.restaurant.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kpb.restaurant.R;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestActivity extends AppCompatActivity {

    @BindView(R.id.rexkdkk)
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
        List<String> mDatas = new ArrayList<>(Arrays.asList("MultiItem ListView",
                "RecyclerView",
                "MultiItem RecyclerView"));

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        CommonAdapter<String> mAdapter = new CommonAdapter<String>(this, R.layout.item_list, mDatas)
        {
            @Override
            protected void convert(ViewHolder holder, String s, int position)
            {
                holder.setText(R.id.id_item_list_title, s + " : " + holder.getAdapterPosition() + " , " + holder.getLayoutPosition());
            }
        };
        recyclerView.setAdapter(mAdapter);
    }
}
