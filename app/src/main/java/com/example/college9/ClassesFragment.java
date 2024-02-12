package com.example.college9;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TodoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClassesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ClassesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TodoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ClassesFragment newInstance(String param1, String param2) {
        ClassesFragment fragment = new ClassesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private ArrayList<String> items = new ArrayList<>();
    private ArrayAdapter<String> itemsAdapter;
    private ListView listView;
    private Button button;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_classes, container, false);
        listView = rootView.findViewById(R.id.listViewClasses);
        button = rootView.findViewById(R.id.buttonClasses);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                addItem(view);
            }
        });
        itemsAdapter = new ArrayAdapter<>(getActivity(), R.layout.row, items);
        listView.setAdapter(itemsAdapter);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setUpListViewListener();
        return rootView;
    }

    private void setUpListViewListener() {
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                // Remove the item within 'items'
                items.remove(position);
                // Notify the adapter to update the ListView
                itemsAdapter.notifyDataSetChanged();
                // Show confirmation toast
                Toast.makeText(getActivity(), "Item Removed", Toast.LENGTH_LONG).show();
                return true;
            }
        });
    }

    private void addItem(View view) {
        EditText classinput = getView().findViewById(R.id.editTextClasses);
        EditText timeinput = getView().findViewById(R.id.editTextTime);
        EditText professorinput = getView().findViewById(R.id.editTextProfessor);
        String timeText = timeinput.getText().toString();
        String classText = classinput.getText().toString();
        String professorText = professorinput.getText().toString();
        if (!classText.equals("") || !timeText.equals("") || !professorText.equals("")) {
            itemsAdapter.add(classText + " with " + professorText + " at " + timeText);
            classinput.setText("");
            professorinput.setText("");
            timeinput.setText("");
        } else {
            Toast.makeText(getActivity(), "Please enter text in each field", Toast.LENGTH_LONG).show(); // Use getActivity() here
        }
    }

}