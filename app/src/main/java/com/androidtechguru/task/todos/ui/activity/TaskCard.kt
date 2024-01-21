package com.androidtechguru.task.todos.ui.activity

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.androidtechguru.task.todos.model.data.Task
import com.androidtechguru.task.todos.util.formattedDate
import com.androidtechguru.task.todos.util.setColor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun TaskCard(task: Task, onClick: suspend (Task) -> Unit = {}) {
    val coroutineScope = rememberCoroutineScope()
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val cardPadding = 12.dp

    Card(
        modifier = Modifier
//            .padding(start = 16.dp, top = 12.dp, bottom = 12.dp)
            .padding(10.dp)
            .heightIn(50.dp, screenWidth * 0.75f)
            .widthIn(50.dp, screenWidth / 2.3f)
            .wrapContentSize()
            .background(Color.White)
            .clickable {
                coroutineScope.launch(Dispatchers.IO) {
                    onClick(task)
                }
            },
//            .requiredHeightIn(min=0.dp,max=screenWidth*0.75f)
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(0.6.dp, Color.Black),
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .wrapContentHeight()
        ) {
            Text(
                text = task.name, modifier = Modifier.fillMaxWidth(),
                fontSize = 20.sp
            )
            Spacer(Modifier.height(16.dp))
            Text(
                text = task.time.toString(),
                color = task.setColor(),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                style = TextStyle(
                )
            )
            Spacer(Modifier.height(30.dp))
            Text(
                text = task.date?.formattedDate() ?: "",
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                textAlign = TextAlign.Justify
            )
        }
    }
}