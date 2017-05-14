import React from 'react'
import { Row, Col } from 'react-bootstrap'
import { SignInForm } from './signin.form'

export class SignInPage extends React.Component {
  render() {
    return(
      <Row>
        <Col xs={11} sm={6} md={4} smOffset={4} mdOffset={4}>
          <SignInForm />
        </Col>
      </Row>
    )
  }
}