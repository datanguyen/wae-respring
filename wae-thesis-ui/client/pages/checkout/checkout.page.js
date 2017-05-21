import React from 'react'
import { connect } from 'react-redux'
import { Row, Col } from 'react-bootstrap'
import { CheckoutForm } from './checkout.form'
import { userCheckoutRequest } from './checkout.action'

@connect(state => ({cart: state.cart}), { userCheckoutRequest })
export default class CheckoutPage extends React.Component {
  render() {
    return (
      <Row>
        <Col xs={12} sm={12} md={12}>
          <CheckoutForm cart={this.props.cart}
            userCheckoutRequest={this.props.userCheckoutRequest}
          />
        </Col>
      </Row>
    )
  }
}

CheckoutPage.propTypes = {
  cart: React.PropTypes.array.isRequired,
  userCheckoutRequest: React.PropTypes.func.isRequired
}


