package pl.pb.r.sm_sqlite.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import pl.pb.r.sm_sqlite.R;
import pl.pb.r.sm_sqlite.api_models.JsonModel;


/**
 * Created by Radosław Naruszewicz on 2016-12-12.
 */

public class JsonAdapter extends RecyclerView.Adapter<JsonAdapter.JsonViewHolder> {
    private static final String IMAGE_URL_BASE = "http://covers.openlibrary.org/b/id/";

    Context mContext;
    LayoutInflater mInflater;
    JSONArray mJsonArray;

    private List<JsonModel> listModel;

    static class JsonViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;
        TextView value;

        public JsonViewHolder(View itemVIew) {
            super(itemVIew);
            name = ((TextView) itemVIew.findViewById(R.id.text_title));
            value = ((TextView) itemVIew.findViewById(R.id.text_author));
            image = ((ImageView) itemVIew.findViewById(R.id.img_thumbnail));
        }
    }

    public JsonAdapter(Context context) {
        mContext = context;
        listModel = new ArrayList<>();
    }

    @Override
    public JsonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_fake_person, parent, false);
        return new JsonViewHolder(view);
    }


    @Override
    public void onBindViewHolder(JsonViewHolder holder, int position) {
        JsonModel model = listModel.get(position);
        holder.name.setText(model.results.get(0).name.first);
        holder.value.setText(model.results.get(0).location.city);
        Picasso.with(mContext)
                .load(String.format(
                        Locale.US,
                        model.results.get(0).picture.medium))
//                .placeholder(R.drawable.)
                .into(holder.image);

    }

    @Override
    public int getItemCount() {
        return listModel.size();
    }

    public void addJson(JsonModel m){
        if(m != null)
        listModel.add(m);
        notifyDataSetChanged();
    }
}
