package org.scoula.weather.dto;

import lombok.Data;

import java.util.List;

@Data
public class WeatherDTO {
    private int visibility;
    private int timezone;
    private Main main;
    private Clouds clouds;
    private Sys sys;
    private int dt;
    private Coord coord;
    private List<WeatherItem> weather;
    private String name;
    private int cod;
    private int id;
    private String base;
    private Wind wind;
}

//{
//		"coord": {
//		"lon": 126.9778,
//		"lat": 37.5683
//		},
//		"weather": [
//		{
//		"id": 802,
//		"main": "Clouds",
//		"description": "구름조금",
//		"icon": "03d"
//		}
//		],
//		"base": "stations",
//		"main": {
//		"temp": 301.91,
//		"feels_like": 303.5,
//		"temp_min": 301.91,
//		"temp_max": 301.93,
//		"pressure": 1005,
//		"humidity": 58,
//		"sea_level": 1005,
//		"grnd_level": 995
//		},
//		"visibility": 10000,
//		"wind": {
//		"speed": 4.12,
//		"deg": 250
//		},
//		"clouds": {
//		"all": 40
//		},
//		"dt": 1750146135,
//		"sys": {
//		"type": 1,
//		"id": 8105,
//		"country": "KR",
//		"sunrise": 1750104626,
//		"sunset": 1750157731
//		},
//		"timezone": 32400,
//		"id": 1835848,
//		"name": "Seoul",
//		"cod": 200
//		}