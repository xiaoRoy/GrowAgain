package com.learn.growagain.codelab.espresso

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.learn.growagain.R

class PhoneNumberFragment: Fragment() {

    private lateinit var etPhoneNumber: EditText
    private lateinit var tvFullPhoneNumberInfo: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_phone_number, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        etPhoneNumber = view.findViewById(R.id.editText_phone_number)
        tvFullPhoneNumberInfo = view.findViewById(R.id.text_phone_info)
        val phoneTypeSpinner = view.findViewById<Spinner>(R.id.spinner_phone_type)


        phoneTypeSpinner.onItemSelectedListener = SimpleOnItemSelectedListener({ parent: AdapterView<*>, _: View, position: Int, _: Long ->
            val selectedPhoneType = parent.getItemAtPosition(position).toString()
            val phoneNumber= etPhoneNumber.text.toString()
            val fullPhoneNumberInfo = "$phoneNumber - $selectedPhoneType"
            tvFullPhoneNumberInfo.text = fullPhoneNumberInfo
            Toast.makeText(view.context, fullPhoneNumberInfo, Toast.LENGTH_LONG).show()
        })
        val phoneTypeAdapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(
            view.context,
            R.array.phone_types,
            android.R.layout.simple_spinner_item
        )

        phoneTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        phoneTypeSpinner.adapter = phoneTypeAdapter

    }

    private class SimpleOnItemSelectedListener(
        private val itemSelected: (AdapterView<*>, View, Int, Long) -> Unit,
        private val nothingSelected: (AdapterView<*>) -> Unit = {}
    ): AdapterView.OnItemSelectedListener {

        override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            itemSelected(parent, view, position, id)
        }

        override fun onNothingSelected(parent: AdapterView<*>) {
            nothingSelected(parent)
        }
    }
}