import React from 'react'
import cssModules from 'react-css-modules'
import { SlideShow } from '../../components'
import style from './explore.style.scss'

@cssModules(style, {errorWhenNotFound: false})
export class ExploreView extends React.Component {



  render() {

    let img1 = require('../../assets/images/home_assets1.png')
    let img2 = require('../../assets/images/home_assets2.jpg')
    let img3 = require('../../assets/images/home_assets3.jpg')

    return (
      <section id="pricing" styleName="pricing-section">
        <section styleName="pricing-section--upper">
          <SlideShow />
        </section>
        <section styleName="pricing-section--intermediate">
          <article styleName="pricing-section__article--vertical">
            <h1 styleName="vertical-article__header">Welcome</h1>
            <span styleName="pricing-section__price-tag">
                <span styleName="icon-money">$</span><span styleName="price-tag" style={{color: 'red'}}>9</span>/Month
            </span>
            <p styleName="vertical-article__content--main">
              Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo
              ligula eget dolor. Aenean massa
            </p>
            <button styleName="vertical-article__button--red" style={{fontStyle: 'normal'}}>Sign In ?</button>
          </article>
          <article styleName="pricing-section__article--vertical">
            <h1 styleName="vertical-article__header">Explore Wae Basics</h1>
            <p styleName="vertical-article__content--main">
              <img src={img1} style={{width: '100%', height: '300px', maxWidth: '300px'}}/>
            </p>
            <button styleName="vertical-article__button--default"
                    style={{backgroundColor: 'rgb(37, 47, 62)'}}>Show All</button>
          </article>
          <article styleName="pricing-section__article--vertical">
            <h1 styleName="vertical-article__header">Free Shipping</h1>
            <p styleName="vertical-article__content--main">
              <img src={img2} style={{width: '100%', height: '300px'}}/>
            </p>
            <button styleName="vertical-article__button--default"
                    style={{backgroundColor: 'rgb(37, 47, 62)'}}>Learn More</button>
          </article>
          <article styleName="pricing-section__article--vertical">
            <h1 styleName="vertical-article__header">Shop by Categories</h1>
            <p styleName="vertical-article__content--main">
              <img src={img3} style={{width: '100%', height: '300px'}}/>
            </p>
            <button styleName="vertical-article__button--default">Explore</button>
          </article>
        </section>
        <section styleName="pricing-section--lower">
          <p styleName="paragraph--lower">Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean
            commodo
            ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis
            parturient montes, nascetur ridiculus mus.<span styleName="color--red"> Learn More.</span></p>
        </section>
      </section>
    )
  }
}
