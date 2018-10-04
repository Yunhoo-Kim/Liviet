package com.liviet.hoo.liviet.ui.food

import android.app.Activity.RESULT_OK
import android.arch.lifecycle.ViewModelProviders
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.liviet.hoo.liviet.R
import com.liviet.hoo.liviet.base.BaseFragment
import com.liviet.hoo.liviet.databinding.FragmentAddNewDietFoodBinding
import com.liviet.hoo.liviet.di.ViewModelFactory
import com.liviet.hoo.liviet.model.nutrition.Food
import com.liviet.hoo.liviet.utils.UiUtli
import com.liviet.hoo.liviet.viewmodel.food.FoodVM
import javax.inject.Inject


class AddNewDietFoodFragment: BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: FoodVM
    private lateinit var binding: FragmentAddNewDietFoodBinding

    private lateinit var mCurrentPhotoPath:String

    private val SELECTIMG = 1234
    private val CAMERA = 1231
    private val CROP = 1232

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_new_diet_food, container, false)
        viewModel = ViewModelProviders.of(activity!!, viewModelFactory).get(FoodVM::class.java)
        binding.viewModel = viewModel
        binding.foodImage.setOnClickListener {
            var builder = AlertDialog.Builder(context!!)
            builder.setItems(R.array.select_image){ dialog, i ->
                when(i){
                    0 -> {
                        requestCamera()
                    }
                    else -> {
                        val intent = Intent(Intent.ACTION_PICK)
                        intent.data = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                        intent.type = "image/*"
                        startActivityForResult(intent, SELECTIMG)
                    }
                }
            }
            builder.show()
        }

        binding.saveFood.setOnClickListener {
            when {
                binding.foodName.text.isNullOrEmpty() -> UiUtli.makeSnackbar(it, R.string.plz_fill_info)
                binding.foodAmountInput.text.isNullOrBlank() -> UiUtli.makeSnackbar(it, R.string.plz_fill_info)
                binding.carbonHydrateAmount.text.isNullOrBlank() -> UiUtli.makeSnackbar(it, R.string.plz_fill_info)
                binding.fatAmount.text.isNullOrBlank() -> UiUtli.makeSnackbar(it, R.string.plz_fill_info)
                binding.proteinAmount.text.isNullOrBlank() -> UiUtli.makeSnackbar(it, R.string.plz_fill_info)
                binding.kcalAmount.text.isNullOrBlank() -> UiUtli.makeSnackbar(it, R.string.plz_fill_info)
                else -> {
                    Log.d("Food info", "$mCurrentPhotoPath ${binding.foodName.text} ${binding.foodAmountInput.text} ${binding.foodAmountMeasure.selectedItem}  ${binding.carbonHydrateAmount.text}  ${binding.fatAmount.text}   ${binding.proteinAmount.text}")
                    var food  = Food(image_url = mCurrentPhotoPath,
                            resource_id = 0,
                            name = binding.foodName.text.toString(),
                            measure = binding.foodAmountMeasure.selectedItem.toString(),
                            carbon_hydrate = binding.carbonHydrateAmount.text.toString().toFloat(),
                            fat = binding.fatAmount.text.toString().toFloat(),
                            protein = binding.proteinAmount.text.toString().toFloat(),
                            amount = binding.foodAmountInput.text.toString().toInt(),
                            cal = binding.kcalAmount.text.toString().toFloat(),
                            na = 0.0f)
                    viewModel.insertFood(food)
                    activity?.onBackPressed()
                }
            }
        }
        return binding.root
    }

    private fun requestCamera(){
        if(ContextCompat.checkSelfPermission(context!!, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(activity!!, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), CAMERA)
        }else if(ContextCompat.checkSelfPermission(context!!, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(activity!!, arrayOf(android.Manifest.permission.CAMERA), CAMERA)
        }else {
            val values = ContentValues(1)
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpg")

            val fileUri = activity?.contentResolver?.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            if (intent.resolveActivity(activity?.packageManager) != null) {
                mCurrentPhotoPath = fileUri.toString()
                intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri)
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION
                        or Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
                startActivityForResult(intent, CAMERA)
            }
        }
    }

    private fun cropImage(uri: Uri){
        val values = ContentValues(1)
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpg")
        val fileUri = activity?.contentResolver?.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)

        val intent = Intent("com.android.camera.action.CROP")
        intent.setDataAndType(uri, "image/*")
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri)
        intent.putExtra("outputX", 500)
        intent.putExtra("outputY", 500)
        intent.putExtra("crop", true)
        intent.putExtra("aspectX", 1)
        intent.putExtra("aspectY", 1)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.parse(mCurrentPhotoPath))
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION
                or Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
        intent.putExtra("return-data", false)
        startActivityForResult(intent, CROP)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK){
            when(requestCode) {
                SELECTIMG -> {
                    mCurrentPhotoPath = data!!.dataString
                    cropImage(Uri.parse(mCurrentPhotoPath))
                }
                CAMERA -> {
                    cropImage(Uri.parse(mCurrentPhotoPath))
                }
                CROP -> {
                    binding.foodImage.setImageURI(Uri.parse(mCurrentPhotoPath))
                }
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when(requestCode){
            CAMERA -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Snackbar.make(view!!, R.string.allow_grant, Snackbar.LENGTH_SHORT).show()
                } else {
                    requestCamera()
                }
            }
        }
    }

    companion object {
        fun newInstance(args: Bundle?): AddNewDietFoodFragment {
            return AddNewDietFoodFragment().apply {
                this.arguments = args
            }
        }
    }
}