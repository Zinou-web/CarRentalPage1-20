package com.example.myapplication.ui.screens.cardetails

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Message
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.ui.theme.poppins
import com.example.myapplication.ui.theme.inter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarDetailsScreen(
    onBackPressed: () -> Unit = {},
    onGalleryClick: () -> Unit = {},
    onBookNow: () -> Unit = {}
) {
    val scrollState = rememberScrollState()
    var isFavorite by remember { mutableStateOf(false) }
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2F5FA))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            // Top Car Image Section with Back Button and Favorite Button
            TopImageSection(
                isFavorite = isFavorite,
                onFavoriteClick = { isFavorite = !isFavorite },
                onBackPressed = onBackPressed
            )
            
            // Car Info Section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF2F5FA))
                    .padding(horizontal = 15.dp, vertical = 10.dp)
            ) {
                // Top Row - Auto tag and Rating
                CarTagAndRating()
                
                // Car Name
                CarNameSection()
                
                // Tabs (About & Gallery)
                TabsSection(onGalleryClick = onGalleryClick)
                
                // Car Renter Info
                CarRenterSection()
                
                // Details Section
                DetailsSection()
            }

            // Price and Book Now Section - Moved outside the column for full width
            PriceAndBookSection(onBookNow = onBookNow)
        }
    }
}

@Composable
fun TopImageSection(
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit,
    onBackPressed: () -> Unit,
    title: String = "Car Details",
    showFavorite: Boolean = true
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(390.dp)
    ) {
        // Main car image
        Image(
            painter = painterResource(id = R.drawable.mustang),
            contentDescription = "Car Image",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
        
        // Title at the top center
        Text(
            text = title,
            fontSize = 23.sp,
            fontFamily = poppins,
            fontWeight = FontWeight.Medium,
            color = Color.Black,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 30.dp)
        )
        
        // Back button
        Box(
            modifier = Modifier
                .padding(start = 15.dp, top = 20.dp)
                .size(45.dp)
                .clip(CircleShape)
                .background(Color(0xFFF2F5FA))
                .border(2.dp, Color.White, CircleShape)
                .align(Alignment.TopStart)
        ) {
            IconButton(
                onClick = onBackPressed,
                modifier = Modifier.size(150.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.Black,
                    modifier = Modifier.size(33.dp)
                )
            }
        }
        
        // Favorite button (optional)
        if (showFavorite) {
            Box(
                modifier = Modifier
                    .padding(end = 15.dp, top = 20.dp)
                    .size(45.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFF2F5FA))
                    .border(2.dp, Color.White, CircleShape)
                    .align(Alignment.TopEnd),
            ) {
                IconButton(
                    onClick = onFavoriteClick,
                ) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = if (isFavorite) Color.Red else Color.Black,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }

        // Car image thumbnails
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 35.dp)
                .fillMaxWidth()
                .padding(horizontal = 28.dp)
                .height(52.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                items(6) { index ->
                    val isMore = index == 5
                    
                    Box(
                        modifier = Modifier
                            .size(45.dp, 45.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        if (isMore) {
                            Text(
                                text = "+99",
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                            )
                        } else {
                            Image(
                                painter = painterResource(
                                    id = when (index) {
                                        0 -> R.drawable.mustang
                                        1 -> R.drawable.audi
                                        2 -> R.drawable.mustang
                                        3 -> R.drawable.audi2
                                        else -> R.drawable.mustang
                                    }
                                ),
                                contentDescription = "Car thumbnail",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CarTagAndRating() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Auto",
            fontSize = 14.sp,
            fontFamily = poppins,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )
        
        // Rating
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.green_star),
                contentDescription = "Rating",
                tint = Color(0xFF149459),
                modifier = Modifier.size(18.dp)
            )
            
            Spacer(modifier = Modifier.width(4.dp))
            
            Text(
                text = "4.9",
                fontSize = 17.sp,
                fontFamily = poppins,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }
}

@Composable
fun CarNameSection() {
    Text(
        text = "Ford Mustang GT Premium 2024",
        fontSize = 19.sp,
        fontFamily = poppins,
        fontWeight = FontWeight.SemiBold,
        color = Color.Black,
        modifier = Modifier.padding(top = 4.dp, bottom = 20.dp)
    )
}

@Composable
fun TabsSection(onGalleryClick: () -> Unit = {}) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "About",
                    fontSize = 18.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            }
            
            Spacer(modifier = Modifier.width(90.dp))
            
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .weight(1f)
                    .clickable { onGalleryClick() }
            ) {
                Text(
                    text = "Gallery",
                    fontSize = 18.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
            }
        }
        
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
        ) {
            // Green divider under "About" (half width)
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(2.dp)
                    .background(Color(0xFF149459))
            )
            
            // Gray divider under "Gallery" (half width)
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(2.dp)
                    .background(Color.LightGray.copy(alpha = 0.5f))
            )
        }
    }
}

@Composable
fun CarRenterSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, bottom = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Avatar
        Box(
            modifier = Modifier
                .size(55.dp)
                .clip(CircleShape)
                .background(Color.White)
                .border(1.dp, Color.LightGray.copy(alpha = 0.3f), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.renterprofile),
                contentDescription = "User",
                tint = Color.Black,
                modifier = Modifier.size(30.dp)
            )
        }
        
        // Renter Info
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 12.dp)
        ) {
            Text(
                text = "Zinou",
                fontSize = 16.sp,
                fontFamily = poppins,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
            
            Text(
                text = "Car renter",
                fontSize = 9.sp,
                fontFamily = poppins,
                fontWeight = FontWeight.SemiBold,
                color = Color.Gray
            )
        }
        
        // Chat Button - using message.xml
        Box(
            modifier = Modifier
                .size(55.dp)
                .clip(CircleShape)
                .background(Color.White)
                .border(1.dp, Color.LightGray.copy(alpha = 0.3f), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.message),
                contentDescription = "Chat",
                tint = Color(0xFF007AFF),
                modifier = Modifier.size(26.dp)
            )
        }
        
        Spacer(modifier = Modifier.width(12.dp))
        
        // Call Button - using call.xml
        Box(
            modifier = Modifier
                .size(55.dp)
                .clip(CircleShape)
                .background(Color.White)
                .border(1.dp, Color.LightGray.copy(alpha = 0.3f), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.call),
                contentDescription = "Call",
                tint = Color(0xFF149459),
                modifier = Modifier.size(26.dp)
            )
        }
    }
}

@Composable
fun DetailsSection() {
    Column {
        // Details Header
        Text(
            text = "details",
            fontSize = 21.sp,
            fontFamily = poppins,
            fontWeight = FontWeight.Normal,
            color = Color.Black,

        )
        
        // Car Description
        Text(
            text = "    The Ford is a powerful and stylish muscle car. It features a 5.0L V8 engine with 480 horsepower, a 6-speed manual transmission. Inside, it offers luxurious seating, a modern curved glass display, and high-tech features.",
            fontSize = 14.sp,
            fontFamily = poppins,
            fontWeight = FontWeight.Normal,
            color = Color.Black.copy(alpha = 0.6f),
        )
    }
}

@Composable
fun PriceAndBookSection(onBookNow: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(top = 17.dp)
            .clip(RoundedCornerShape(0.dp))
            .background(Color(0xFFF2F5FA))
            .border(
                width = 2.dp,
                color = Color.White,
                shape = RoundedCornerShape(15.dp)
            )
            .padding(vertical = 20.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Price",
                    fontSize = 15.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                )
                
                Text(
                    text = "00.00DA/hr",
                    fontSize = 21.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                )
            }
            
            Spacer(modifier = Modifier.weight(1f))
            
            // Book Now Button
            Button(
                onClick = { onBookNow() },
                modifier = Modifier
                    .height(40.dp)
                    .width(150.dp),
                shape = RoundedCornerShape(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF149459)
                )
            ) {
                Text(
                    text = "Book Now",
                    fontSize = 18.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CarDetailsScreenPreview() {
    CarDetailsScreen()
} 