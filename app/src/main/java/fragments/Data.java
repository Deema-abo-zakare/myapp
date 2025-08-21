package fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapp.R;

public class Data extends Fragment {

    private Button myButton;
    private Button myButton1;
    private Button myButton2;

    private int count0 = 3; // الرقم الابتدائي للزر الأول
    private int count1 = 3; // الرقم الابتدائي للزر الثاني
    private int count2 = 3; // الرقم الابتدائي للزر الثالث

    public Data() {
        // Required empty public constructor
    }

    public static Data newInstance(String param1, String param2) {
        Data fragment = new Data();
        Bundle args = new Bundle();
        args.putString("param1", param1);
        args.putString("param2", param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // نعمل Inflate للـ layout تبع الـ Fragment
        return inflater.inflate(R.layout.fragment_data, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // نربط الأزرار من الواجهة
        myButton = view.findViewById(R.id.myButton);
        myButton1 = view.findViewById(R.id.myButton1);
        myButton2= view.findViewById(R.id.myButton2);

        // ضبط النص الابتدائي لكل زر
        myButton.setText(String.valueOf(count0));
        myButton1.setText(String.valueOf(count1));
        myButton2.setText(String.valueOf(count2));

        // إعداد حدث الضغط لكل زر
        setupButton(myButton, 0);
        setupButton(myButton1, 1);
        setupButton(myButton2, 2);
    }

    private void setupButton(Button button, int index) {
        button.setOnClickListener(v -> {
            int count;
            switch (index) {
                case 0: count = count0; break;
                case 1: count = count1; break;
                case 2: count = count2; break;
                default: count = 0;
            }

            if (count > 0) {
                count--;

                // تحديث النص
                button.setText(String.valueOf(count));

                // عند وصوله إلى صفر، يصبح أحمر
                if (count == 0) {
                    button.setBackgroundColor(
                            getResources().getColor(android.R.color.holo_red_dark)
                    );
                }

                // حفظ القيمة الجديدة
                switch (index) {
                    case 0: count0 = count; break;
                    case 1: count1 = count; break;
                    case 2: count2 = count; break;
                }
            }
        });
    }
}
