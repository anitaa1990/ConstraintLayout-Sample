# ConstraintLayout-Sample
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Constraint%20Layout%20Demo-blue.svg?style=flat)](https://android-arsenal.com/details/3/7050)

A demo app to showcase constraint layout implementation in Android

<h3>Build a Responsive UI with ConstraintLayout</h3>

<img src="https://github.com/anitaa1990/ConstraintLayout-Sample/blob/new_changes/media/demo.gif" width="300" style="max-width:100%;"></br></br>

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

<b>2. Some commonly used attributes if ConstraintLayout:</b>
</br>  a. <b>layout_constraintTop_toTopOf</b> — Align the top of the desired view to the top of another.</br>
  b. <b>layout_constraintTop_toBottomOf</b> — Align the top of the desired view to the bottom of another</br>
  c. <b>layout_constraintBottom_toTopOf</b> — Align the bottom of the desired view to the top of another.</br>
  d. <b>layout_constraintBottom_toBottomOf</b> — Align the bottom of the desired view to the bottom of another.</br>
  e. <b>layout_constraintLeft_toTopOf</b> — Align the left of the desired view to the top of another.</br>
  f. <b>layout_constraintLeft_toBottomOf</b> — Align the left of the desired view to the bottom of another.</br>
  g. <b>layout_constraintLeft_toLeftOf</b> — Align the left of the desired view to the right of another.</br>
  h. <b>layout_constraintRight_toTopOf</b> — Align the right of the desired view to the top of another.</br>
  i. <b>layout_constraintRight_toBottomOf</b> — Align the right of the desired view to the bottom of another.</br>
  j. <b>layout_constraintRight_toLeftOf</b> — Align the right of the desired view to the left of another.</br>
  k. <b>layout_constraintRight_toRightOf</b> — Align the right of the desired view to the right of another.</br>  
<h4>Note: Within a ConstraintLayout, side margins for a child view will only take effect if that side is constrained to another view.  </h4>


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
</br> <b>Using Horizontal Bias:</b> This is to position a view along the horizontal axis using a bias value, this will be relative to it’s constrained position. For example: ``` app:layout_constraintHorizontal_bias="0.5" ``` will center a view horizontally.
</br> <b>Using Vertical Bias:</b> This is to position a view along the vertical axis using a bias value, this will be relative to it’s constrained position. For example: ``` app:app:layout_constraintVertical_bias="0.5" ``` will center a view vertically.
</br></br>


<b>4. How to resize a view?</b>
</br> Using ```app:layout_constraintWidth_default="wrap" (with width set to 0dp)```
.If set, the widget will have the same size as if using wrap_content, but will be limited by constraints (i.e. it won't expand beyond them) 

</br>

All of the above steps can be found in the article [here](https://medium.com/@anitaa_1990/learning-to-implement-constraintlayout-in-android-8ddc69fe0a1a)



