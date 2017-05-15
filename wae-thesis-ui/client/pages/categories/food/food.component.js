import React from 'react'
import { connect } from 'react-redux'
import { FoodView } from './food.view'

@connect(state => ({foodApi: state.food }))
export class Food extends React.Component {
  render() {
    return(
      <FoodView  foodApi={this.props.foodApi}/>
    )
  }
}
