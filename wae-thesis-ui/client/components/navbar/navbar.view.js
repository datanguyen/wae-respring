import React from 'react'
import {Link} from 'react-router'
import cssModules from 'react-css-modules'
import { InputGroup, InputGroupButton, Input} from "reactstrap"
import { Glyphicon, Col } from "react-bootstrap"
import style from "./navbar.style.scss"
import { CartComponent } from '../cart'

@cssModules(style, {errorWhenNotFound: false})
export class NavbarView extends React.Component {
  render() {
    let {user, authenticated} = this.props.authenticate

    const userLinks = (
      <div>
        <Link styleName="nav-bar__link--default" to="/#">Hello {user.username}</Link>
      </div>
    )

    const guestLinks = (
      <div>
        <Link styleName="nav-bar__link--default" to="/signin">Sign In</Link>
        <Link styleName="nav-bar__link--default" to="/signup">Sign Up Free</Link>
      </div>
  )

    return (
      <div styleName="nav-bar--fixed">
        <Col lg={2}>
          <Link to="/">
            <div styleName="nav-bar__logo--left-fixed">
              <span styleName="nav-bar--red">W</span>A<span style={{color: 'yellow'}}>D</span>E
            </div>
          </Link>
        </Col>
        <Col lg={6}>
          <div styleName="nav-bar__list--right">
            <InputGroup>
              <InputGroupButton color="warning">
                <button className="btn" styleName="btn-yellow">What you are looking for</button>
              </InputGroupButton>
              <Input placeholder="............"/>
              <InputGroupButton color="success">
                <button className="btn" styleName="btn-yellow">
                  <Glyphicon glyph="eye-open"/> Search
                </button>
              </InputGroupButton>
            </InputGroup>
          </div>
        </Col>
        <Col lg={4}>
          <div styleName="nav-bar__list--right">
            { authenticated ? userLinks : guestLinks }
            <CartComponent />
            </div>
        </Col>
      </div>
    )
  }
}

NavbarView.propTypes = {
  authenticate: React.PropTypes.object.isRequired,
}
