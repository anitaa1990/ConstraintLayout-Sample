# ConstraintLayout-Sample
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Constraint%20Layout%20Demo-blue.svg?style=flat)](https://android-arsenal.com/details/3/7050)

A demo app to showcase constraint layout implementation in Android

Please checkout the medium article [here](https://medium.com/@anitaa_1990/learning-to-implement-constraintlayout-in-android-8ddc69fe0a1a) for a detailed explanation on how to build the above user interface.

<img src="https://github.com/anitaa1990/ConstraintLayout-Sample/blob/new_changes/media/demo.gif" width="300" style="max-width:100%;"></br></br>


<h3>Some of the common concepts in ConstraintLayout</h3>

<b>1. How to set Aspect Ratio:</b>

Using ``` app:layout_constraintDimensionRatio="H,3:1" ```

H,3:1 will always make the ImageView appear 3 times wider than height. The prefix H or W tells ConstraintLayout which dimension should respect the ratio. If it is H then it means width will be first computed from other constraints and then height will be adjusted according to the aspect ratio. 

``` 
<ImageView
    android:id="@+id/image"
    android:layout_width="0dp"
    android:layout_height="0dp"
    android:scaleType="centerCrop"
    app:layout_constraintDimensionRatio="H,16:9"
    app:layout_constraintEnd_toEndOf="parent"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintTop_toTopOf="parent" />
```
</br>  

<b>2. Some commonly used attributes in ConstraintLayout:</b>
</br>  
  a. <b>layout_constraintEnd_toEndOf</b> — The end of the widget will be aligned to the end of the parent view.</br>
  b. <b>layout_constraintStart_toStartOf</b> — The start of the widget will be aligned to the start of the parent view.</br>
  c. <b>layout_constraintTop_toTopOf</b> — The top of the widget will be aligned to the top of the parent view.</br>
  d. <b>layout_constraintTop_toBottomOf</b> — The top of the widget will be aligned to the bottom of the parent view.</br>
  e. <b>layout_constraintBottom_toTopOf</b> — The bottom of the widget will be aligned to the top of the parent view.</br>
  f. <b>layout_constraintBottom_toBottomOf</b> — The bottom of the widget will be aligned to the bottom of the parent view. </br>
  g. <b>layout_constraintLeft_toTopOf</b> — The left of the widget will be aligned to the top of the parent view.</br>
  h. <b>layout_constraintLeft_toBottomOf</b> — The left of the widget will be aligned to the bottom of the parent view.</br>
  i. <b>layout_constraintLeft_toLeftOf</b> — The left of the widget will be aligned to the left of the parent view.</br>
  j. <b>layout_constraintLeft_toRightOf</b> — The left of the widget will be aligned to the right of the parent view.</br>
  k. <b>layout_constraintRight_toTopOf</b> — The right of the widget will be aligned to the top of the parent view.</br>
  l. <b>layout_constraintRight_toBottomOf</b> — The right of the widget will be aligned to the bottom of the parent view.</br>
  m. <b>layout_constraintRight_toLeftOf</b> — The right of the widget will be aligned to the left of the parent view.</br>
  n. <b>layout_constraintRight_toRightOf</b> — The right of the widget will be aligned to the right of the parent view.</br>  
<h4>Note: Within a ConstraintLayout, side margins for a child view will only take effect if that side is constrained to another view.</h4>


      <!-- From the below example you can see that if we need to add a textView below the ImageView, 
           then we need to add  app:layout_constraintTop_toBottomOf="@+id/profile_image" this line.
           Alternatively, if we need to add margin between the imageview and textview, we need to add a
           constraint between the textView and the imageView -->
           
      <android.support.constraint.ConstraintLayout
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:orientation="vertical">
        
        <com.an.constraintlayout.views.CircleImageView
            android:id="@+id/profile_image"
            android:layout_width="@dimen/profile_item_image_size"
            android:layout_height="@dimen/profile_item_image_size"
            app:layout_constraintHorizontal_bias="0.5"
            android:layout_marginLeft="@dimen/margin"
            android:layout_marginRight="@dimen/margin"
            app:layout_constraintLeft_toRightOf="parent"
            app:layout_constraintRight_toLeftOf="parent"
            app:civ_border_color="@android:color/transparent"
            app:civ_border_width="0dp" />

        <com.an.customfontview.CustomTextView
            android:id="@+id/txt_name"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginLeft="@dimen/margin"
            android:layout_marginRight="@dimen/margin"
            android:layout_marginTop="@dimen/margin_small"
            android:ellipsize="end"
            android:maxEms="6"
            android:maxLines="1"
            android:textColor="@color/profile_item_name"
            android:textSize="@dimen/font_small"
            app:layout_constraintHorizontal_bias="0.5"
            app:layout_constraintLeft_toRightOf="parent"
            app:layout_constraintRight_toLeftOf="parent"
            app:layout_constraintTop_toBottomOf="@+id/profile_image"
            app:textFontPath="fonts/gt_medium.otf" />


    </android.support.constraint.ConstraintLayout>
   
</br> 

<b>3. How to center a view vertically or horizontally?</b>
</br> <b>Using Horizontal Bias:</b> This means that the position of a view along the horizontal axis can be defined using a bias value. For example: ``` app:layout_constraintHorizontal_bias="0.5" ``` will center a view horizontally.
</br> <b>Using Vertical Bias:</b> This means that the position of a view along the vertical axis can be defined using a bias value. For example: ``` app:layout_constraintVertical_bias="0.5" ``` will center a view vertically.
</br></br>


<b>4. How to resize a view?</b>
</br> Using ```app:layout_constrainedHeight="true"```
This will wrap the CardView height according to its contents.
</br> Using ```app:layout_constrainedWidth="true"```
This will wrap the CardView width according to its contents.
</br>

You can checkout some of the other constraints we have not looked at in this article.</br>
* [Chains](https://developer.android.com/reference/android/support/constraint/ConstraintLayout#Chains)
* [Guideline](https://developer.android.com/reference/android/support/constraint/Guideline)
* [Dimension Constraints](https://developer.android.com/reference/android/support/constraint/ConstraintLayout#DimensionConstraints)
* [Circular Positioning](https://developer.android.com/reference/android/support/constraint/ConstraintLayout#CircularPositioning)
* [Visibility Behaviour](https://developer.android.com/reference/android/support/constraint/ConstraintLayout#VisibilityBehavior)





