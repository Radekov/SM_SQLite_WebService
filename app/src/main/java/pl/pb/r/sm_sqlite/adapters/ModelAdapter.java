package pl.pb.r.sm_sqlite.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import pl.pb.r.sm_sqlite.R;
import pl.pb.r.sm_sqlite.database.Model;

/**
 * Created by Radosław Naruszewicz on 2016-12-12.
 */

public class ModelAdapter extends RecyclerView.Adapter<ModelAdapter.ModelViewHolder>{

    private List<Model> listModel;
    private ModelClickListener clickListener;
    public interface ModelClickListener {
        void onModelClick(int position);
    }

    static class ModelViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView value;

        public ModelViewHolder(View itemVIew, final ModelClickListener clickListener){
            super(itemVIew);
            name= ((TextView) itemVIew.findViewById(R.id.modelName));
            value= ((TextView) itemVIew.findViewById(R.id.modelValue));
            itemVIew.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if(clickListener != null){
                        clickListener.onModelClick(getAdapterPosition());
                    }
                    return false;
                }
            });
        }
    }
    public ModelAdapter(ModelClickListener clickListener)
    {
        this.clickListener = clickListener;
        listModel = new ArrayList<>();
    }

    @Override
    public ModelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_value,parent,false);
        return new ModelViewHolder(view,clickListener);
    }


    @Override
    public void onBindViewHolder(ModelViewHolder holder, int position) {
        Model model = listModel.get(position);
        holder.name.setText(model.getName());
        String value = String.format(Locale.US,"%.2f",model.getValue());
//        Log.w("Value:",Double.toString(model.getValue()));
//        Log.w("Value:",value);
        holder.value.setText(value);

    }

    @Override
    public int getItemCount() {
        return listModel.size();
    }

    public void setListModel(List<Model> list){
        this.listModel = list;
    }

    public void addModel(Model m){
        if(listModel == null)
            return;
        listModel.add(m);
        notifyDataSetChanged();
    }
    public void removeModel(Model m){
        listModel.remove(m);
        notifyDataSetChanged();
    }
    public void updateModel(Model m, int position){

        listModel.set(position,m);
        notifyItemChanged(position);


    }

    public Model getModel(int position){
        return listModel.get(position);
    }

}
