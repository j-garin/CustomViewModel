package com.jgarin.customviewmodeltestapp

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class CustomViewModelActivity : AppCompatActivity() {

	private val number by lazy { intent.getIntExtra("param", 0) }
	private val viewModel by lazy { CustomViewModelProvider.of(this).getViewModel(CustomViewModel::class.java, CustomViewModelParams(number)) }
	private val listener: (Int) -> Unit = { param ->
		Intent(this, CustomViewModelActivity::class.java)
				.apply { putExtra("param", param) }
				.let { startActivity(it) }
	}
	private val adapter = NumberAdapter(listener)

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		btnSwitch.text = "Check out Google's ViewModel"
		btnSwitch.setOnClickListener {
			Intent(this, GoogleViewModelActivity::class.java)
					.apply { flags = Intent.FLAG_ACTIVITY_CLEAR_TASK and Intent.FLAG_ACTIVITY_NEW_TASK }
					.let { startActivity(it) }
		}

		recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
		recycler.adapter = adapter

		viewModel.getStateLiveData().observe(this, Observer { it?.let { renderState(it) } })

	}

	private fun renderState(state: CustomViewModel.State) {
		textView.text = state.number.toString()
		adapter.setData(state.otherNumbers)
	}

}