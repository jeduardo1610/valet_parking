package com.example.valetparking.register_vehicle.presentation.components

import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.applyCanvas
import com.example.valetparking.R
import java.io.File

data class Line(
    val start: Offset,
    val end: Offset,
    val color: Color = Color.Red,
    val strokeWidth: Dp = 4.dp
)

@Composable
fun VehicleDamageScreen(onRegisterDone: (Bitmap, File) -> Unit, loading: Boolean) {
    val lines = remember {
        mutableStateListOf<Line>()
    }

    val buttonVisible = remember {
        mutableStateOf(true)
    }

    Box {

        if (loading) {
            Loader()
            return
        }

        Image(
            modifier = Modifier.fillMaxSize(),
            imageVector = ImageVector.vectorResource(R.drawable.car_map),
            contentDescription = ""
        )

        Canvas(modifier = Modifier
            .background(Color.Transparent)
            .fillMaxSize()
            .pointerInput(key1 = true) {
                detectDragGestures { change, dragAmount ->
                    change.consume()
                    val line = Line(start = change.position - dragAmount, end = change.position)
                    lines.add(line)
                }
            }
        ) {
            lines.forEach { line ->
                drawLine(
                    color = line.color,
                    start = line.start,
                    end = line.end,
                    strokeWidth = line.strokeWidth.toPx(),
                    cap = StrokeCap.Round
                )

            }
        }

        val view = LocalView.current
        val context = LocalContext.current

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            fontWeight = FontWeight.Bold,
            color = Color.Red,
            text = "Continue",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(0.dp)
                .fillMaxWidth()
                .alpha(if (buttonVisible.value) 1f else 0f)
                .clickable {
                    buttonVisible.value = false
                    val handler = Handler(Looper.getMainLooper())
                    handler.postDelayed({
                        val bmp = Bitmap
                            .createBitmap(
                                view.width, view.height,
                                Bitmap.Config.ARGB_8888
                            )
                            .applyCanvas {
                                view.draw(this)
                            }
                        bmp.let {
                            onRegisterDone(
                                it,
                                ContextWrapper(context).getDir("vehicles", Context.MODE_PRIVATE)
                            )
                        }
                    }, 1000)
                }
        )
    }
}
