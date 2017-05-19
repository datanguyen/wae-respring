import React from 'react'
import { Row, Col } from 'react-bootstrap'
import { connect } from 'react-redux'
import { userSignUpRequest } from './signup.action'
import { SignUpForm } from './signup.form'

 class SignUpPage extends React.Component {
  render() {
    return(
      <Row>
        <Col xs={11} sm={6} md={4} smOffset={4} mdOffset={4}>
          <SignUpForm userSignUpRequest={ this.props.userSignUpRequest }/>
        </Col>
      </Row>
    )
  }
}

SignUpPage.propTypes = {
  userSignUpRequest: React.PropTypes.func.isRequired
}

export default connect(null, { userSignUpRequest })(SignUpPage)

