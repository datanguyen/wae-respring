import React from 'react'
import { Row, Col } from 'react-bootstrap'
import { connect } from 'react-redux'
import { userSignInRequest } from './signin.action'
import { SignInForm } from './signin.form'

 class SignInPage extends React.Component {
  render() {
    const { userSignInRequest } = this.props
    return(
      <Row>
        <Col xs={11} sm={6} md={4} smOffset={4} mdOffset={4}>
          <SignInForm userSignInRequest={userSignInRequest} />
        </Col>
      </Row>
    )
  }
}

SignInPage.propTypes = {
  userSignInRequest: React.PropTypes.func.isRequired
}

export default connect(null, { userSignInRequest })(SignInPage)

