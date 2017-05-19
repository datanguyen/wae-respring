const express = require('express')
const compression = require('compression')
const webpack = require('webpack')
const webpackDevMiddleware = require('webpack-dev-middleware')
const webpackHotMiddleware = require('webpack-hot-middleware')
const config = require('../webpack/config')
const app = express()
const host = config.settings.host || '0.0.0.0'
const port = config.settings.port || 8000
const env = process.env.NODE_ENV || 'development'
const isDevMode = env.toLowerCase() !== 'production'

app.use(compression())

app.set('views', `${__dirname}`)
app.set('view engine', 'pug')

/* Case DEV mode */
if (isDevMode) {
  const compiler = webpack(config)

  app.use(webpackHotMiddleware(compiler))
  app.use(webpackDevMiddleware(compiler, {
    noInfo: true,
    publicPath: config.output.publicPath,
    headers: {
      'Access-Control-Allow-Origin': '*'
    },
    stats: {
      colors: true,
      timings: true
    }
  }))
} else {
  /* Case PRODUCTION mode */
  app.use(express.static(config.settings.distPath))
}

const initialState = {
  "subModuleId": "fetch-product",
  "moduleId": "product",
  "app": {
    "path": "/product/fetch-product",
    "authenticate":{
      "user":{
        "username":"anonymousUser",
        "authorities":null
      },
      "authenticated":false
    },
    "subModuleId": "fetch-product",
    "functionId": null,
    "data": {
      "handmadeApi": {
        "category": "Handmade",
        "products": [{
          "productName": "Gold Swarovski Pearl",
          "productPrice": 35.99,
          "productImgUrl": "http://localhost:8002/image/hand_1.jpg"
        }, {
          "productName": "Blue Octopus Upcycled",
          "productPrice": 999.0,
          "productImgUrl": "http://localhost:8002/image/hand_2.jpg"
        }, {
          "productName": "Family tree chart",
          "productPrice": 216.6,
          "productImgUrl": "http://localhost:8002/image/hand_3.jpg"
        }, {
          "productName": "Bracelet with Silver Rings",
          "productPrice": 109.95,
          "productImgUrl": "http://localhost:8002/image/hand_4.jpg"
        }, {
          "productName": "Family Tree Jewelry",
          "productPrice": 199.99,
          "productImgUrl": "http://localhost:8002/image/hand_5.jpg"
        }, {
          "productName": "Graduation Bracelet",
          "productPrice": 21.87,
          "productImgUrl": "http://localhost:8002/image/hand_6.jpg"
        }, {
          "productName": "Custom Message Cuff Bracelet",
          "productPrice": 859.66,
          "productImgUrl": "http://localhost:8002/image/hand_7.jpg"
        }, {
          "productName": "Bracelet with Silver Rings",
          "productPrice": 109.95,
          "productImgUrl": "http://localhost:8002/image/hand_4.jpg"
        }, {
          "productName": "Family Tree Jewelry",
          "productPrice": 199.99,
          "productImgUrl": "http://localhost:8002/image/hand_5.jpg"
        }, {
          "productName": "Gold Swarovski Pearl",
          "productPrice": 35.99,
          "productImgUrl": "http://localhost:8002/image/hand_1.jpg"
        }, {
          "productName": "Blue Octopus Upcycled",
          "productPrice": 999.0,
          "productImgUrl": "http://localhost:8002/image/hand_2.jpg"
        }, {
          "productName": "Blue Octopus Upcycled",
          "productPrice": 999.0,
          "productImgUrl": "http://localhost:8002/image/hand_7.jpg"
        }]
      },
      "electronicApi": {
        "category": "Electronics and TVs",
        "products": [{
          "productName": "Acer Aspire",
          "productPrice": 349.99,
          "productImgUrl": "http://localhost:8002/image/tech_acer.jpg"
        }, {
          "productName": "Acer Aspire",
          "productPrice": 999.0,
          "productImgUrl": "http://localhost:8002/image/tech_aspire.jpg"
        }, {
          "productName": "HP Pro Book",
          "productPrice": 216.6,
          "productImgUrl": "http://localhost:8002/image/tech_hp.jpg"
        }, {
          "productName": "NETGEAR Nighthawk",
          "productPrice": 109.95,
          "productImgUrl": "http://localhost:8002/image/tech_router.jpg"
        }, {
          "productName": "LG 32MA68HY-P 32-Inch",
          "productPrice": 199.99,
          "productImgUrl": "http://localhost:8002/image/tech_lg.jpg"
        }, {
          "productName": "Acer Aspire Desktop",
          "productPrice": 21.87,
          "productImgUrl": "http://localhost:8002/image/acer_desktop.jpg"
        }, {
          "productName": "CYBERPOWERPC Gamer XtremeC",
          "productPrice": 859.66,
          "productImgUrl": "http://localhost:8002/image/tech_cyber.jpg"
        }, {
          "productName": "LG 32MA68HY-P 32-Inch",
          "productPrice": 199.99,
          "productImgUrl": "http://localhost:8002/image/tech_lg.jpg"
        }, {
          "productName": "Acer Aspire",
          "productPrice": 999.0,
          "productImgUrl": "http://localhost:8002/image/tech_aspire.jpg"
        }, {
          "productName": "HP Pro Book",
          "productPrice": 216.6,
          "productImgUrl": "http://localhost:8002/image/tech_hp.jpg"
        }, {
          "productName": "LG 32MA68HY-P 32-Inch",
          "productPrice": 199.99,
          "productImgUrl": "http://localhost:8002/image/tech_lg.jpg"
        }, {
          "productName": "Acer Aspire",
          "productPrice": 999.0,
          "productImgUrl": "http://localhost:8002/image/tech_aspire.jpg"
        }]
      },
      "foodApi": {
        "category": "Handmade",
        "products": [{
          "productName": "Gold Swarovski Pearl",
          "productPrice": 35.99,
          "productImgUrl": "http://localhost:8002/image/hand_1.jpg"
        }, {
          "productName": "Blue Octopus Upcycled",
          "productPrice": 999.0,
          "productImgUrl": "http://localhost:8002/image/hand_2.jpg"
        }, {
          "productName": "Family tree chart",
          "productPrice": 216.6,
          "productImgUrl": "http://localhost:8002/image/hand_3.jpg"
        }, {
          "productName": "Bracelet with Silver Rings",
          "productPrice": 109.95,
          "productImgUrl": "http://localhost:8002/image/hand_4.jpg"
        }, {
          "productName": "Family Tree Jewelry",
          "productPrice": 199.99,
          "productImgUrl": "http://localhost:8002/image/hand_5.jpg"
        }, {
          "productName": "Graduation Bracelet",
          "productPrice": 21.87,
          "productImgUrl": "http://localhost:8002/image/hand_6.jpg"
        }, {
          "productName": "Custom Message Cuff Bracelet",
          "productPrice": 859.66,
          "productImgUrl": "http://localhost:8002/image/hand_7.jpg"
        }, {
          "productName": "Bracelet with Silver Rings",
          "productPrice": 109.95,
          "productImgUrl": "http://localhost:8002/image/hand_4.jpg"
        }, {
          "productName": "Family Tree Jewelry",
          "productPrice": 199.99,
          "productImgUrl": "http://localhost:8002/image/hand_5.jpg"
        }, {
          "productName": "Gold Swarovski Pearl",
          "productPrice": 35.99,
          "productImgUrl": "http://localhost:8002/image/hand_1.jpg"
        }, {
          "productName": "Blue Octopus Upcycled",
          "productPrice": 999.0,
          "productImgUrl": "http://localhost:8002/image/hand_2.jpg"
        }, {
          "productName": "Blue Octopus Upcycled",
          "productPrice": 999.0,
          "productImgUrl": "http://localhost:8002/image/hand_7.jpg"
        }]
      }
    },
    "moduleId": "product"
  }
};


app.get('*', (req, res) => {
  res.render('index', {initialState})
})


app.listen(port, config.settings.host, error => {
  if (error) {
    console.info("⛔ ⛔ ⛔  *** ERROR *** ⛔ ⛔ ⛔")
    console.error(error)
  } else {
    console.info("✅ ✅ ✅  *** %s mode's started *** ✅ ✅ ✅", env.toUpperCase())
    console.info("✅ ✅ ✅  *** Listening at http://%s:%s *** ✅ ✅ ✅", host, port)
  }
})
