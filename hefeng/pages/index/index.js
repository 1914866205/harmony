Page({
    data: {
        region: ['江苏省', '镇江市', '丹徒区'],
        nowInfo: [{
            name: "温度",
            num: '',
        } ,{
            name: "气压",
            num: ''
        }, {
            name: "能见度",
            num: ''
        }, {
            name: "风向",
            num: ''
        }, {
            name: "风速",
            num: ''
        }, {
            name: "风力",
            num: ''
        }],
        temp: 0,
        weather: '晴',
        imgsrc:'999-fill',
        localID: 101190305
    },
    regionChange(e) {
        // 通过位置选择器，获取指定的位置
        this.setData({
            region: e.detail.value,
        })
        //设置当前位置的位置ID
        this.getLocationID(e.detail.value[1], e.detail.value[2])
        //获取当前位置ID的天气信息
        this.getInfo(this.localID)
    },
    getLocationID(city, area) {
        var that = this
        wx.request({
            url: 'https://geoapi.qweather.com/v2/city/lookup?',
            method: "GET",
            data: {
                location: area,
                adm: city,
                key: 'fea4e184dc894626a21b4a408a8e09ed'
            },
            success: function (res) {
                that.getInfo(res.data.location[0].id)
                that.setData({
                    localID: res.data.location[0].id
                })
                console.log("当前localID:" + res.data.location[0].id)
            }
        })
    },
    /*根据 LocalID获取信息 */
    getInfo(id) {
        var that = this
        wx.request({
            url: 'https://devapi.qweather.com/v7/weather/now?',
            data: {
                location: id,
                key: 'fea4e184dc894626a21b4a408a8e09ed'
            },
            success(res) {
                var resdata = res.data.now
                console.log(resdata)
                var restemp = resdata.temp
                that.setData({
                    temp: restemp,
                    weather: resdata.text,
                    imgsrc: resdata.icon,
                    nowInfo: [{
                        name: "湿度",
                        num: resdata.humidity + '%'
                    }, {
                        name: "气压",
                        num: resdata.pressure + 'hPa'
                    }, {
                        name: "能见度",
                        num: resdata.vis + 'km'
                    }, {
                        name: "风向",
                        num: resdata.windDir
                    }, {
                        name: "风速",
                        num: resdata.windSpeed + 'km/h'
                    }, {
                        name: "风力",
                        num: resdata.windScale + '级'
                    }]
                })
            }
        })
    }
    ,onLoad() {
        this.getLocationID('镇江市','丹徒区')
        this.getInfo(101190305)
      },



})