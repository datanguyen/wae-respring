import React from 'react'
import { Col, Alert } from 'react-bootstrap'
import cssModules from 'react-css-modules'
import style from './food.style.scss'

import { CateBarComponent, ProductBox } from '../../../components'

@cssModules(style, { errorWhenNotFound: false})
export class FoodView extends React.Component {

  render() {
    let { category, products } = this.props.foodApi
    return(
      <div>
        <section id="pricing" styleName="home-section">
          <section styleName="home-section--upper">
            <Col lg={2} styleName="upper--left">
              <div styleName="home__drop-down-list">
                <button styleName="dd-list__title"><b>Departments</b> <span className="caret"/></button>
                <div styleName="dd-list__content">
                  <CateBarComponent />
                </div>
              </div>
            </Col>
            <Col lg={6} styleName="upper--middle">
              <a styleName="link-helper--default">Your Wade.com</a>
              <a styleName="link-helper--default">Services & Memberships</a>
              <a styleName="link-helper--default">Gifts & Sales</a>
              <a styleName="link-helper--default">Try prime & Protection</a>
            </Col>
            <Col lg={4} styleName="upper--right">
              <div styleName="supplier__drop-down-list">
                <button styleName="dd-list__title">Suppliers<span className="caret"/></button>
                <div styleName="dd-list__content">
                  <a href="#" styleName="help-link">Trade Services</a>
                  <a href="#" styleName="help-link">Work with us ?</a>
                  <a href="#" styleName="help-link">Make Profit</a>
                </div>
              </div>
              <div styleName="help__drop-down-list">
                <button styleName="dd-list__title">Help<span className="caret"/></button>
                <div styleName="dd-list__content">
                  <a href="#" styleName="help-link">For Buyers</a>
                  <a href="#" styleName="help-link">For Suppliers</a>
                  <a href="#" styleName="help-link">For New Users</a>
                </div>
              </div>
            </Col>
          </section>
        </section>
        <section id="pricing" styleName="pricing-section">
          <section styleName="pricing-section--upper" style={{padding: "30px", fontSize: "100px"}}>
            <h1 className="animated bounceInDown">All {category} products listed below!</h1>
          </section>
          <ProductBox products={products} />
          <section styleName="pricing-section--lower">
            <p styleName="paragraph--lower">Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean
              commodo
              ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis
              parturient montes, nascetur ridiculus mus.<span styleName="color--red"> Learn More.</span></p>
          </section>
        </section>
      </div>
    )
  }
}

FoodView.propTypes = {
  foodApi: React.PropTypes.object.isRequired
}