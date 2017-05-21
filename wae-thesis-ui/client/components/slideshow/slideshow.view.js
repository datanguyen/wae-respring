import React from 'react'
import cssModules from 'react-css-modules'
import { Carousel, CarouselItem } from 'react-bootstrap'
import style from './slideshow.style.scss'

@cssModules(style, { errorWhenNotFound: false })
export class SlideShowView extends React.Component {
  render() {
    let imgUrls = [
      require('../../assets/images/slide5.jpg'),
      require('../../assets/images/slide2.jpg'),
      require('../../assets/images/slide6.jpg'),
      require('../../assets/images/slide3.jpg'),
      require('../../assets/images/slide8.jpg'),
      require('../../assets/images/slide4.jpg'),
      require('../../assets/images/slide1.jpg')
    ]

    return (
      <section style={{width: '100%'}}>
        <Carousel interval={2500}>
          {
            imgUrls.map(url => {
              return (
                <CarouselItem>
                  <img styleName="fade" style={{width: '100%'}} src={url} />
                </CarouselItem>
              )
            })
          }
        </Carousel>
      </section>
    )
  }
}