package sg.edu.rp.c346.id22022505.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etToDo;
    Spinner spinner;
    Button btnAdd;
    Button btnDel;
    Button btnClear;
    ListView listViewToDo;

    ArrayList<String> alToDo;
    ArrayAdapter<String> aaToDo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etToDo = findViewById(R.id.editTextText);
        spinner = findViewById(R.id.SpinnerToDo);
        btnAdd = findViewById(R.id.btnAdd);
        btnDel = findViewById(R.id.btnDel);
        btnClear = findViewById(R.id.btnClear);
        listViewToDo = findViewById(R.id.listViewToDo);

        alToDo = new ArrayList<String>();
        ArrayAdapter aaToDo = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alToDo);
        listViewToDo.setAdapter(aaToDo);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = etToDo.getText().toString();
                alToDo.add(item);
                aaToDo.notifyDataSetChanged();
                etToDo.setText(""); // Clear the EditText after adding the item
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = Integer.parseInt(etToDo.getText().toString());
                if (pos >= 0 && pos < alToDo.size()) {
                    alToDo.remove(pos);
                    aaToDo.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity.this, "Invalid to-do item", Toast.LENGTH_SHORT).show();
                }
                etToDo.setText("");
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alToDo.clear();
                aaToDo.notifyDataSetChanged();
            }
        });

        listViewToDo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = alToDo.get(position);
                Toast.makeText(MainActivity.this, item, Toast.LENGTH_SHORT).show();
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if (selectedItem.equals("Add a new task")) {
                    btnDel.setEnabled(false);
                    btnAdd.setEnabled(true);
                    btnClear.setEnabled(true);
                    Toast.makeText(MainActivity.this, "Add a new task selected", Toast.LENGTH_SHORT).show();
                } else {
                    btnDel.setEnabled(true);
                    btnAdd.setEnabled(false);
                    btnClear.setEnabled(true);
                    Toast.makeText(MainActivity.this, "Delete a task selected", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(MainActivity.this, "No task selected", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
