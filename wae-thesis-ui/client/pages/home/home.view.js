import React from 'react'
import cssModules from 'react-css-modules'
import { Col } from "react-bootstrap"

import style from './home.style.scss'
import {CateBarComponent} from "../../components"
import {Explore} from "../explore"

@cssModules(style, {errorWhenNotFound: false})
export class HomeView extends React.Component {
    render() {
        return (
          <div>
              <section id="pricing" styleName="home-section">
                  <section styleName="home-section--upper">
                      <Col lg={2} styleName="upper--left">
                          <div styleName="home__drop-down-list">
                              <button styleName="dd-list__title"><b>Departments</b> <span className="caret"/></button>
                              <div styleName="dd-list__content" className="animated bounceInLeft">
                                  <CateBarComponent/>
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
              <Explore/>
          </div>
        )
    }
}
