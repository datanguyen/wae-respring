import React from 'react'
import cssModules from 'react-css-modules'
import style from './slideshow.style.scss'

@cssModules(style, { errorWhenNotFound: false })
export class SlideShowView extends React.Component {
  render() {
    let imgUrls = [
      require('../../assets/images/slide5.jpg'),
      // require('../../assets/images/slide2.jpg'),
      // require('../../assets/images/slide3.jpg'),
      // require('../../assets/images/slide4.jpg')
    ]

    return (
      <section style={{width: '100%'}}>
        {
          imgUrls.map(url => {
            return <img styleName="fade" style={{width: '100%'}} src={url} />
          })
        }
      </section>
    )
  }
}