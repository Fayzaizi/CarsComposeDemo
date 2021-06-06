package com.jako.lee.carscomposedemo.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.jako.lee.carscomposedemo.R
import com.jako.lee.carscomposedemo.data.Car
import com.jako.lee.carscomposedemo.data.garage

/**
 * Created by Fayzaizi on 2021/6/6.
 */

private val logoSize = 50.dp

@Composable
fun CarsHome() {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.app_name))
                })
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = "cars/search"
        ) {
            composable("cars/search") {
                CarsSearchScreen {
                    navController.navigate("cars/result?content=${it}")
                }
            }
            composable(
                "cars/result?content={content}",
                arguments = listOf(
                    navArgument("content") {
                        type = NavType.StringType
                    })
            ) {
                val content = it.arguments?.getString("content")
                CarsResults(content)
            }
        }
    }
}

@Composable
fun CarsSearchScreen(onSearch: (String) -> Unit) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(top = 50.dp)
    ) {
        val modifier = Modifier.align(Alignment.CenterHorizontally)
        WelcomeHint(modifier)
        CarsSearchBar(modifier, onSearch)
    }
}


@Composable
fun WelcomeHint(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.ic_cars_logo),
            contentDescription = "Cars Logo",
            modifier = modifier
                .clip(RoundedCornerShape(logoSize / 2))
                .width(logoSize)
                .height(logoSize)
        )
        Text(
            text = "寻找喜欢的汽车总动员",
            modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 16.dp)
        )
    }
}

@Composable
fun CarsSearchBar(modifier: Modifier = Modifier, onSearch: (String) -> Unit) {
    Row(modifier = modifier.padding(16.dp)) {
        var input by remember { mutableStateOf("") }
        OutlinedTextField(
            value = input,
            onValueChange = { text: String -> input = text },
            placeholder = { Text("输入名字或者颜色") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = object : (KeyboardActionScope) -> Unit {
                override fun invoke(scope: KeyboardActionScope) {
                    onSearch(input)
                }
            }),
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(bottom = 9.dp)
        )
        Button(
            onClick = { onSearch(input) },
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(start = 8.dp)
                .width(70.dp)
                .height(48.dp)
        ) {
            Text(text = "Go!")
        }
    }
}

@Preview
@Composable
fun CarsHomePreview() {
    CarsHome()
    //CarsResults()
}

@Composable
fun CarsResults(searchContent: String?) {
    val results = garage
    val listState = rememberLazyListState()
    LazyColumn(state = listState) {
        items(results.size) { CarItem(results[it]) }
    }
}

@Composable
fun CarItem(car: Car) {
    Row(modifier = Modifier.padding(16.dp)) {
        Image(
            painter = painterResource(id = car.image),
            contentDescription = "Cars Logo",
            modifier = Modifier
                .clip(RoundedCornerShape(logoSize / 2))
                .width(logoSize)
                .height(logoSize)
        )
        Column(
            modifier = Modifier
                .padding(start = 16.dp)
                .align(Alignment.CenterVertically)
        ) {
            Text(text = car.name)
            Text(text = car.color)
        }
    }
}