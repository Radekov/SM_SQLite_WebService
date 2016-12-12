package pl.pb.r.sm_sqlite.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import pl.pb.r.sm_sqlite.R;
import pl.pb.r.sm_sqlite.adapters.ModelAdapter;
import pl.pb.r.sm_sqlite.database.Model;
import pl.pb.r.sm_sqlite.database.ModelsDataSource;

public class MainActivity extends AppCompatActivity implements ModelAdapter.ModelClickListener{

    List<Model> listModels;
    ModelAdapter modelAdapter;
    ModelsDataSource modelsDataSource;

    RecyclerView recyclerView;
    EditText etName;
    EditText etValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        recyclerView = ((RecyclerView) findViewById(R.id.recyclerView));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        etName = ((EditText) findViewById(R.id.etName));
        etValue = ((EditText) findViewById(R.id.etValue));

        listModels = new ArrayList<>();
        modelsDataSource = new ModelsDataSource(getApplicationContext());
        modelsDataSource.open();

        listModels.add(modelsDataSource.createModel(new Model("Dora",5.99)));
        listModels.add(modelsDataSource.createModel(new Model("Radek",0.99)));
        listModels.add(modelsDataSource.createModel(new Model("Double",9.99)));
        listModels.add(modelsDataSource.createModel(new Model("Float",19.99)));
        listModels.add(modelsDataSource.createModel(new Model("onCreate",0.59)));
        listModels.add(modelsDataSource.createModel(new Model("SM",0.01)));

        modelAdapter = new ModelAdapter(this);
        modelAdapter.setListModel(listModels);
        recyclerView.setAdapter(modelAdapter);
    }

    @Override
    protected void onDestroy() {
        List<Model> toDelete = modelsDataSource.getAllModels();
        for (Model d :
                toDelete) {
            modelsDataSource.deleteModel(d);
        }
        super.onDestroy();
    }

    public void addModel(View v){
        boolean isEmpty = false;
        if(etName.getText().length() <= 0){
            isEmpty = true;
            etName.setError("Postaw nazwę");
        }
        if(etValue.getText().length() <= 0){
            isEmpty = true;
            etValue.setError("Być powinno oznaczone liczbą");
        }
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        if(isEmpty)return;
        Model model = (modelsDataSource.createModel(etName.getText().toString(), Double.parseDouble(etValue.getText().toString())));
        //listModels.add(model);
       modelAdapter.addModel(model);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.file:
                Intent intent = new Intent(this, Main2Activity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onModelClick(final int position) {
        Log.d("ClickPosition",position + ": " +modelAdapter.getModel(position));
        final Model m = modelAdapter.getModel(position);

        // custom dialog
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.edit_model_dialog);
        dialog.setTitle("Zdecyduj o tej wartości");

        // set the custom dialog components - text, image and button
        final EditText editModelName = (EditText) dialog.findViewById(R.id.editModelName);
        editModelName.setText(m.getName());

        final EditText editModelValue = (EditText) dialog.findViewById(R.id.editModelValue);
        editModelValue.setText(Double.toString(m.getValue()));

        Button dialogButtonOk = (Button) dialog.findViewById(R.id.dialogButtonOK);
        // if button is clicked, close the custom dialog
        dialogButtonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isEmpty = false;
                if(editModelName.getText().length() <= 0){
                    isEmpty = true;
                    editModelName.setError("Postaw nazwę");
                }
                if(editModelValue.getText().length() <= 0){
                    isEmpty = true;
                    editModelValue.setError("Być powinno oznaczone liczbą");
                }
                if(isEmpty) return;
                m.setName(editModelName.getText().toString());
                m.setValue(Double.parseDouble(editModelValue.getText().toString()));
                modelsDataSource.updateModel(m);
                modelAdapter.updateModel(m,position);
                dialog.dismiss();
            }
        });
        Button dialogButtonNeutral = (Button) dialog.findViewById(R.id.dialogButtonNeutral);
        dialogButtonNeutral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        Button dialogButtonNegative = (Button) dialog.findViewById(R.id.dialogButtonNegative);
        dialogButtonNegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modelsDataSource.deleteModel(m);
                modelAdapter.removeModel(m);
                dialog.dismiss();
            }
        });

        dialog.show();


    }
}
