package com.jjclade.therappy;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class GetHelp extends Main {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Get Help");

        if (savedInstanceState == null) {
            switchFragmentTo(1);
        }
    }

    private void switchFragmentTo(int pageNumber) {
        FragmentTransaction tx=getFragmentManager().beginTransaction();

        switch (pageNumber) {
            case 1:
                tx.replace(R.id.content_frame, new Page1());
                break;
            case 2:
                tx.replace(R.id.content_frame, new Page2());
                break;
            case 3:
                tx.replace(R.id.content_frame, new Page3());
                break;
            case 4:
                tx.replace(R.id.content_frame, new Page4());
                break;
            case 5:
                tx.replace(R.id.content_frame, new Page5());
                break;
            default:
                Toast.makeText(getApplicationContext(), "There's no page for that", Toast.LENGTH_SHORT);
                return;
        }

        tx.commit();
    }

    private class Page1 extends Fragment {
        public Page1() {
            // empty as required for Fragment subclasses
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView=inflater.inflate(R.layout.fragment_get_help, container, false);

            ImageButton button=(ImageButton)(rootView.findViewById(R.id.next_button));
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // TODO: check that a mood is entered
                    switchFragmentTo(2);
                }
            });
            Button add=(Button)(rootView.findViewById(R.id.add));
            add.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // TODO: check that a mood is entered
                    switchFragmentTo(3);
                }
            });



//            Button button1=(Button)(rootView.findViewById(R.id.next_button));
//            button.setOnClickListener(new View.OnClickListener() {
//                public void onClick(View v) {
//                    // TODO: check that a mood is entered
//                    switchFragmentTo(2);
//                }
//            });

            return rootView;
        }

        private LogMoods activity;
    }

    private class Page2 extends Fragment {
        public Page2() {
            // empty as required for Fragment subclasses
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView=inflater.inflate(R.layout.fragment_get_help2, container, false);

            Button button=(Button)(rootView.findViewById(R.id.next_button));
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // TODO: check that a mood is entered
                    switchFragmentTo(1);
                }
            });

            return rootView;
        }
    }


    private class Page5 extends Fragment {
        public Page5() {
            // empty as required for Fragment subclasses
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView=inflater.inflate(R.layout.fragment_get_helpmom, container, false);

            Button button=(Button)(rootView.findViewById(R.id.next_button));
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // TODO: check that a mood is entered
                    switchFragmentTo(4);
                }
            });

            return rootView;
        }
    }

    private class Page3 extends Fragment {
        public Page3() {
            // empty as required for Fragment subclasses
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView=inflater.inflate(R.layout.fragment_get_help3, container, false);

            Button button=(Button)(rootView.findViewById(R.id.next_button));
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // TODO: check that a mood is entered
                    switchFragmentTo(4);
                }
            });

            return rootView;
        }
    }

    private class Page4 extends Fragment {
        public Page4() {
            // empty as required for Fragment subclasses
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView=inflater.inflate(R.layout.fragment_get_helpother, container, false);

            ImageButton button=(ImageButton)(rootView.findViewById(R.id.next_button));
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // TODO: check that a mood is entered
                    switchFragmentTo(2);
                }
            });

            ImageButton mom=(ImageButton)(rootView.findViewById(R.id.imageButton5));
            mom.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // TODO: check that a mood is entered
                    switchFragmentTo(5);
                }
            });

            Button add=(Button)(rootView.findViewById(R.id.addnew));
            add.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // TODO: check that a mood is entered
                    switchFragmentTo(3);
                }
            });


            return rootView;
        }
    }


}
