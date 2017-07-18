package sp.com.mvpdemo.presenter_view.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.greenrobot.greendao.annotation.NotNull;

import java.lang.ref.SoftReference;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import sp.com.mvpdemo.R;
import sp.com.mvpdemo.modle.MusicBean;

/**
 * Created by songyuan on 2017/7/18.
 */

public class MusicListAdapter extends BaseAdapter {
    private SoftReference<Activity> softActivity = null;
    private List<MusicBean> list = null;
    private LayoutInflater layoutInflater = null;

    public MusicListAdapter(@NotNull SoftReference<Activity> softActivity, @NotNull List<MusicBean> list) {
        this.softActivity = softActivity;
        this.list = list;
        layoutInflater = ((Activity) softActivity.get()).getLayoutInflater();
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();
            convertView = layoutInflater.inflate(R.layout.item_music, parent, false);
            ButterKnife.bind(holder, convertView);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        Picasso.with(softActivity.get()).load(list.get(position).getImage()).into(holder.iv_music_image);
        holder.tv_music_name.setText(list.get(position).getTitle());
        holder.tv_author.setText(list.get(position).getAuthor().get(0).getName());

        return convertView;
    }

    class Holder {
        @Bind(R.id.tv_music_name)
        TextView tv_music_name;
        @Bind(R.id.tv_author)
        TextView tv_author;
        @Bind(R.id.iv_music_image)
        ImageView iv_music_image;
    }
}
